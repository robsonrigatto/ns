package br.com.rr.campanha.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rr.campanha.domain.Campanha;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
	
	Campanha findByNome(String nome);
	List<Campanha> findByTimeId(Long timeId);
	
	@Query("select c from Campanha c where c.inicioVigencia <= :termino and c.terminoVigencia >= :inicio order by c.terminoVigencia")
	List<Campanha> findBetweenPeriodoVigencia(@Param("inicio") Date inicioVigencia, @Param("termino") Date terminoVigencia);
}
