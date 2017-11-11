package br.com.rr.sociotorcedor.service;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rr.sociotorcedor.domain.Cliente;
import br.com.rr.sociotorcedor.domain.ClienteCampanha;
import br.com.rr.sociotorcedor.dto.ClienteDTO;
import br.com.rr.sociotorcedor.enumeration.Time;
import br.com.rr.sociotorcedor.exception.ClienteAlreadyExistsException;
import br.com.rr.sociotorcedor.repository.ClienteCampanhaRepository;
import br.com.rr.sociotorcedor.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteCampanhaRepository clienteCampanhaRepository;
	
	@Test
	public void createClienteCorinthians() throws ClienteAlreadyExistsException {
		Long clienteId = this.clienteService.create(new ClienteDTO(null, "corinthians@gmail.com", "João da Silva", new Date(), Time.CORINTHIANS.getId()));
		Assert.assertNotNull(clienteId);
		
		Cliente cliente = clienteRepository.findOne(clienteId);
		List<ClienteCampanha> campanhas = clienteCampanhaRepository.findByCliente(cliente);
		
		Assert.assertFalse(campanhas.isEmpty());
		Assert.assertEquals(2, campanhas.size());
		Assert.assertEquals(1l, (long) campanhas.get(0).getId().getCampanhaId());
		Assert.assertEquals(2l, (long) campanhas.get(1).getId().getCampanhaId());
	}
	
	@Test
	public void createClientePalmeiras() throws ClienteAlreadyExistsException {
		Long clienteId = this.clienteService.create(new ClienteDTO(null, "palmeiras@gmail.com", "Pedro Paulo", new Date(), Time.PALMEIRAS.getId()));
		Assert.assertNotNull(clienteId);
		
		Cliente cliente = clienteRepository.findOne(clienteId);
		List<ClienteCampanha> campanhas = clienteCampanhaRepository.findByCliente(cliente);
		
		Assert.assertFalse(campanhas.isEmpty());
		Assert.assertEquals(2, campanhas.size());
		Assert.assertEquals(3l, (long) campanhas.get(0).getId().getCampanhaId());
		Assert.assertEquals(4l, (long) campanhas.get(1).getId().getCampanhaId());
	}
	
	@Test
	public void createClienteSantos() throws ClienteAlreadyExistsException {
		Long clienteId = this.clienteService.create(new ClienteDTO(null, "santos@gmail.com", "José Lima", new Date(), Time.SANTOS.getId()));
		Assert.assertNotNull(clienteId);
		
		Cliente cliente = clienteRepository.findOne(clienteId);
		List<ClienteCampanha> campanhas = clienteCampanhaRepository.findByCliente(cliente);
		
		Assert.assertFalse(campanhas.isEmpty());
		Assert.assertEquals(1, campanhas.size());
		Assert.assertEquals(5l, (long) campanhas.get(0).getId().getCampanhaId());
	}

}
