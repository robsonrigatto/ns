package br.com.rr.sociotorcedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rr.sociotorcedor.domain.Cliente;
import br.com.rr.sociotorcedor.domain.ClienteCampanha;
import br.com.rr.sociotorcedor.domain.pk.ClienteCampanhaId;

public interface ClienteCampanhaRepository extends JpaRepository<ClienteCampanha, ClienteCampanhaId> {
	
	@Query("select cc from ClienteCampanha cc where cc.id.cliente = :cliente order by cc.id.campanhaId")
	List<ClienteCampanha> findByCliente(@Param("cliente") Cliente cliente);

	@Query("select cc from ClienteCampanha cc where cc.id.campanhaId = :campanhaId")
	List<ClienteCampanha> findByCampanhaId(@Param("campanhaId") Long campanhaId);
	
	@Modifying
	@Query("delete from ClienteCampanha where id.cliente = :cliente")
	void deleteByCli(@Param("cliente") Cliente cliente);
}
