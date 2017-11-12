package br.com.rr.campanha.integration.mock;

import org.springframework.stereotype.Component;

import br.com.rr.campanha.domain.Campanha;
import br.com.rr.campanha.integration.CampanhaIntegration;

@Component
public class CampanhaIntegrationMock implements CampanhaIntegration {

	@Override
	public void updateCampanha(Campanha campanha) {
		System.out.println("campanha updated in 3rd system");
	}

}
