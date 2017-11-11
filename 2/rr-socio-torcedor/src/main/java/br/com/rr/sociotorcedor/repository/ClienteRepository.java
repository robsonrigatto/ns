package br.com.rr.sociotorcedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rr.sociotorcedor.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findByEmail(String email);

}
