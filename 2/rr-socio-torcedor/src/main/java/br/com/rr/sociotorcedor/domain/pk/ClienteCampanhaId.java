package br.com.rr.sociotorcedor.domain.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.rr.sociotorcedor.domain.Cliente;

@Embeddable
public class ClienteCampanhaId implements Serializable {
	
	private static final long serialVersionUID = 1051026355115556224L;

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;

	private Long campanhaId;
	
	public ClienteCampanhaId() {
		//
	}
	
	public ClienteCampanhaId(Cliente cliente, Long campanhaId) {
		this.campanhaId = campanhaId;
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Long getCampanhaId() {
		return campanhaId;
	}
	public void setCampanhaId(Long campanhaId) {
		this.campanhaId = campanhaId;
	}
}
