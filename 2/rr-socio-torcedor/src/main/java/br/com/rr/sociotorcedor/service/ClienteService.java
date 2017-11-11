package br.com.rr.sociotorcedor.service;

import br.com.rr.sociotorcedor.dto.ClienteDTO;
import br.com.rr.sociotorcedor.exception.ClienteAlreadyExistsException;

public interface ClienteService {
	
	Long create(ClienteDTO clienteDTO) throws ClienteAlreadyExistsException;
	ClienteDTO retrieve(Long clienteId);
	void delete(Long id);
	ClienteDTO update(ClienteDTO campanhaDTO);

}
