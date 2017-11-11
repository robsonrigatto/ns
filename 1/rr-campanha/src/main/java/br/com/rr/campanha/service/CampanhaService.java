package br.com.rr.campanha.service;

import java.util.List;

import br.com.rr.campanha.dto.CampanhaDTO;
import br.com.rr.campanha.exception.CampanhaAlreadyExistsException;
import br.com.rr.campanha.exception.CampanhaNotFoundException;

public interface CampanhaService {
	
	Long create(CampanhaDTO campanhaDTO) throws CampanhaAlreadyExistsException;
	CampanhaDTO update(CampanhaDTO campanhaDTO) throws CampanhaNotFoundException;
	void delete(Long campanhaId);
	CampanhaDTO retrieve(Long campanhaId);
	List<CampanhaDTO> retrieveByTime(Long timeId);
}
