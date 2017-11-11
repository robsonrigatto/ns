package br.com.rr.sociotorcedor.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rr.sociotorcedor.domain.Cliente;
import br.com.rr.sociotorcedor.domain.ClienteCampanha;
import br.com.rr.sociotorcedor.domain.pk.ClienteCampanhaId;
import br.com.rr.sociotorcedor.dto.ClienteDTO;
import br.com.rr.sociotorcedor.enumeration.Time;
import br.com.rr.sociotorcedor.exception.ClienteAlreadyExistsException;
import br.com.rr.sociotorcedor.exception.IntegrationException;
import br.com.rr.sociotorcedor.integration.CampanhaIntegration;
import br.com.rr.sociotorcedor.repository.ClienteCampanhaRepository;
import br.com.rr.sociotorcedor.repository.ClienteRepository;
import br.com.rr.sociotorcedor.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteCampanhaRepository clienteCampanhaRepository;
	
	@Autowired
	private CampanhaIntegration campanhaIntegration;
	
	@Override
	@Transactional
	public Long create(ClienteDTO clienteDTO) throws ClienteAlreadyExistsException {
		Cliente entity = this.clienteRepository.findByEmail(clienteDTO.getEmail());
		if(entity != null) {
			throw new ClienteAlreadyExistsException();
		}
		
		entity = new Cliente();
		entity.setEmail(clienteDTO.getEmail());
		entity.setNomeCompleto(clienteDTO.getNomeCompleto());
		entity.setDataNascimento(clienteDTO.getDataNascimento());
		entity.setTime(Time.findById(clienteDTO.getTimeId()));
		
		entity = this.clienteRepository.save(entity);
		
		try {
			List<Long> campanhaIds = this.campanhaIntegration.findByTime(entity.getTime());
			for(Long cId : campanhaIds) {
				ClienteCampanha cc = new ClienteCampanha();
				cc.setId(new ClienteCampanhaId(entity, cId));
				this.clienteCampanhaRepository.save(cc);
			}
		} catch (IntegrationException e) { //when connection has failed
			//
		}

		
		return entity.getId();
	}

	@Override
	public ClienteDTO retrieve(Long clienteId) {
		Cliente entity = clienteRepository.findOne(clienteId);
		if(entity == null) {
			throw new EntityNotFoundException();
		}
		return this.entityToDTO(entity);
	}

	@Override
	@Transactional
	public ClienteDTO update(ClienteDTO clienteDTO) {
		Cliente entity = this.clienteRepository.findOne(clienteDTO.getId());
		if(entity == null) {
			throw new EntityNotFoundException();
		}
		
		entity.setNomeCompleto(clienteDTO.getNomeCompleto());
		entity.setDataNascimento(clienteDTO.getDataNascimento());
		entity.setTime(Time.findById(clienteDTO.getTimeId()));
		
		entity = this.clienteRepository.save(entity);
		
		try {
			List<Long> campanhaIds = this.campanhaIntegration.findByTime(entity.getTime());
			this.clienteCampanhaRepository.deleteByCli(entity);
			
			for(Long cId : campanhaIds) {
				ClienteCampanha cc = new ClienteCampanha();
				cc.setId(new ClienteCampanhaId(entity, cId));
				this.clienteCampanhaRepository.save(cc);
			}
		} catch (IntegrationException e) { //when connection has failed
			//
		}
		
		return this.entityToDTO(entity);
	}

	@Override
	public void delete(Long id) {
		this.clienteRepository.delete(id);
	}
	
	private ClienteDTO entityToDTO(Cliente entity) {
		ClienteDTO dto = new ClienteDTO(entity.getId(), entity.getEmail(), entity.getNomeCompleto(), 
				entity.getDataNascimento(), entity.getTime().getId());
		return dto;
	}

}
