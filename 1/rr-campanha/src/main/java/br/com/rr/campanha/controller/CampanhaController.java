package br.com.rr.campanha.controller;

import java.util.List;

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

import br.com.rr.campanha.dto.CampanhaDTO;
import br.com.rr.campanha.exception.CampanhaAlreadyExistsException;
import br.com.rr.campanha.exception.CampanhaNotFoundException;
import br.com.rr.campanha.service.CampanhaService;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {

	@Autowired
	private CampanhaService campanhaService;
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody CampanhaDTO campanhaDTO, UriComponentsBuilder ucBuilder) {
		Long campanhaId;

		try {
			campanhaId = this.campanhaService.create(campanhaDTO);
		
		} catch (CampanhaAlreadyExistsException e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/campanhas/{id}").buildAndExpand(campanhaId).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CampanhaDTO> retrieve(@PathVariable("id") Long id) {
		CampanhaDTO dto = this.campanhaService.retrieve(id);
		return new ResponseEntity<CampanhaDTO>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/times/{id}")
	public ResponseEntity<List<CampanhaDTO>> retrieveByTime(@PathVariable("id") Long timeId) {
		List<CampanhaDTO> campanhas = this.campanhaService.retrieveByTime(timeId);
		return new ResponseEntity<List<CampanhaDTO>>(campanhas, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<CampanhaDTO> update(@PathVariable("id") Long id, @RequestBody CampanhaDTO campanhaDTO) {
		CampanhaDTO updatedDTO = null;
		try {
			campanhaDTO.setId(id);
			updatedDTO = this.campanhaService.update(campanhaDTO);
			
		} catch (CampanhaNotFoundException e) {
			return new ResponseEntity<CampanhaDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CampanhaDTO>(updatedDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CampanhaDTO> delete(@PathVariable("id") Long id) {
		this.campanhaService.delete(id);
		return new ResponseEntity<CampanhaDTO>(HttpStatus.NO_CONTENT);
	}
}
