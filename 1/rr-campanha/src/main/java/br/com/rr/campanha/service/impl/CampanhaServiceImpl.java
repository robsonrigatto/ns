package br.com.rr.campanha.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rr.campanha.domain.Campanha;
import br.com.rr.campanha.dto.CampanhaDTO;
import br.com.rr.campanha.exception.CampanhaAlreadyExistsException;
import br.com.rr.campanha.exception.CampanhaNotFoundException;
import br.com.rr.campanha.helper.TerminoVigenciaHelper;
import br.com.rr.campanha.integration.CampanhaIntegration;
import br.com.rr.campanha.repository.CampanhaRepository;
import br.com.rr.campanha.service.CampanhaService;

@Service
public class CampanhaServiceImpl implements CampanhaService {

	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	private CampanhaIntegration clienteIntegration;
	
	@Autowired
	private TerminoVigenciaHelper terminoVigenciaHelper;

	@Override
	@Transactional
	public Long create(CampanhaDTO campanhaDTO) throws CampanhaAlreadyExistsException {
		String nome = campanhaDTO.getNome();
		Date inicioVigencia = campanhaDTO.getInicioVigencia();
		Date terminoVigencia = campanhaDTO.getTerminoVigencia();
		
		Campanha entity = this.campanhaRepository.findByNome(nome);
		if(entity != null) {
			throw new CampanhaAlreadyExistsException();
		}
		
		List<Campanha> campanhas = this.campanhaRepository.findBetweenPeriodoVigencia(inicioVigencia, terminoVigencia);
		Map<Long, Date> mapIncrement = terminoVigenciaHelper.incrementTermino(campanhas, terminoVigencia);
		
		for(Campanha c : campanhas) {
			Date newTerminoVigencia = mapIncrement.get(c.getId());
			c.setTerminoVigencia(newTerminoVigencia);
			c = this.campanhaRepository.save(c);
			
			this.clienteIntegration.updateCampanha(c);
		}
		
		entity = new Campanha();
		entity.setNome(nome);
		entity.setTimeId(campanhaDTO.getTimeId());
		entity.setInicioVigencia(inicioVigencia);
		entity.setTerminoVigencia(terminoVigencia);
		entity = this.campanhaRepository.save(entity);
		return entity.getId();
	}

	@Override
	public CampanhaDTO update(CampanhaDTO campanhaDTO) throws CampanhaNotFoundException {
		Campanha entity = this.campanhaRepository.findOne(campanhaDTO.getId());
		if(entity == null) {
			throw new CampanhaNotFoundException();
		}
		
		entity.setNome(campanhaDTO.getNome());
		entity.setTimeId(campanhaDTO.getTimeId());
		entity.setInicioVigencia(campanhaDTO.getInicioVigencia());
		entity.setTerminoVigencia(campanhaDTO.getTerminoVigencia());
		entity = this.campanhaRepository.saveAndFlush(entity);
		
		this.clienteIntegration.updateCampanha(entity);
		
		return this.entityToDTO(entity);
	}

	@Override
	public void delete(Long campanhaId) {
		this.campanhaRepository.delete(campanhaId);
	}

	@Override
	public CampanhaDTO retrieve(Long campanhaId) {
		Campanha entity = this.campanhaRepository.findOne(campanhaId);
		if(entity == null) {
			throw new EntityNotFoundException();
		}
		return entityToDTO(entity);
	}

	@Override
	public List<CampanhaDTO> retrieveByTime(Long timeId) {
		List<Campanha> campanhas = this.campanhaRepository.findByTimeId(timeId);
		List<CampanhaDTO> dtos = new ArrayList<>();
		
		campanhas.stream().forEach(c -> {
			dtos.add(this.entityToDTO(c));
		});

		return dtos;
	}

	private CampanhaDTO entityToDTO(Campanha entity) {
		CampanhaDTO dto = null;
		
		if(entity != null) {
			dto = new CampanhaDTO(entity.getId(), entity.getNome(), entity.getTimeId(), entity.getInicioVigencia(), entity.getTerminoVigencia());
		}
		
		return dto;
	}
}
