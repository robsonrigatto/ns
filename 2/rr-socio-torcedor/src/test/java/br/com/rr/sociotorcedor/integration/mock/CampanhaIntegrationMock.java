package br.com.rr.sociotorcedor.integration.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.rr.sociotorcedor.enumeration.Time;
import br.com.rr.sociotorcedor.exception.IntegrationException;
import br.com.rr.sociotorcedor.integration.CampanhaIntegration;

@Component
@Primary
public class CampanhaIntegrationMock implements CampanhaIntegration {

	@Override
	public List<Long> findByTime(Time time) throws IntegrationException {
		List<Long> ids = new ArrayList<>();
		
		switch (time) {
			case CORINTHIANS:
				ids.add(1l); ids.add(2l);
				break;
			
			case PALMEIRAS:
				ids.add(3l); ids.add(4l);
				break;
			default:
				ids.add(5l);
		}
		return ids;
	}

}
