package br.com.rr.sociotorcedor.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.rr.sociotorcedor.domain.pk.ClienteCampanhaId;

@Entity
public class ClienteCampanha {
	
	@Id
	private ClienteCampanhaId id;
	
	public ClienteCampanhaId getId() {
		return id;
	}

	public void setId(ClienteCampanhaId id) {
		this.id = id;
	}
}
