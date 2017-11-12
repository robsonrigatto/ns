package br.com.rr.sociotorcedor.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.rr.sociotorcedor.dto.ClienteDTO;
import br.com.rr.sociotorcedor.enumeration.Time;
import br.com.rr.sociotorcedor.exception.ClienteAlreadyExistsException;
import br.com.rr.sociotorcedor.exception.IntegrationException;
import br.com.rr.sociotorcedor.integration.CampanhaIntegration;
import br.com.rr.sociotorcedor.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CampanhaIntegration campanhaIntegration;
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody ClienteDTO clienteDTO, UriComponentsBuilder ucBuilder) {
		Long clienteId;

		try {
			clienteId = this.clienteService.create(clienteDTO);
		
		} catch (ClienteAlreadyExistsException e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/clientes/{id}").buildAndExpand(clienteId).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> retrieve(@PathVariable("id") Long id) {
		try {
			ClienteDTO dto = this.clienteService.retrieve(id);
			return new ResponseEntity<ClienteDTO>(dto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<ClienteDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}/campanhas")
	public ResponseEntity<List<Long>> retrieveCampanhas(@PathVariable("id") Long id) {
		ClienteDTO dto = this.clienteService.retrieve(id);
		
		try {
			List<Long> campanhas = this.campanhaIntegration.findByTime(Time.findById(dto.getTimeId()));
			return new ResponseEntity<List<Long>>(campanhas, HttpStatus.OK);
		
		} catch (IntegrationException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Long>>(HttpStatus.NOT_FOUND);
	}
		
	@PostMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable("id") Long id, @RequestBody ClienteDTO campanhaDTO) {
		ClienteDTO updatedDTO = null;
		try {
			campanhaDTO.setId(id);
			updatedDTO = this.clienteService.update(campanhaDTO);
			
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<ClienteDTO>(HttpStatus.NOT_FOUND);
		
		} catch (Exception e) {
			return new ResponseEntity<ClienteDTO>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ClienteDTO>(updatedDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable("id") Long id) {
		this.clienteService.delete(id);
		return new ResponseEntity<ClienteDTO>(HttpStatus.NO_CONTENT);
	}
}
