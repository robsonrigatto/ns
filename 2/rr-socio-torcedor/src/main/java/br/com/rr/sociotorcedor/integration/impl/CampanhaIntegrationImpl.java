package br.com.rr.sociotorcedor.integration.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.rr.sociotorcedor.enumeration.Time;
import br.com.rr.sociotorcedor.integration.CampanhaIntegration;

@Component
public class CampanhaIntegrationImpl implements CampanhaIntegration {

	@Override
	public List<Long> findByTime(Time time) {
		RestTemplate restTemplate = new RestTemplate();
		List<?> campanhas = restTemplate.getForObject("http://localhost:8080/campanhas/times/" + time.getId(), List.class);
		List<Long> campanhaIds = new ArrayList<>();
		
		campanhas.forEach(c -> {
			Map propertiesMap = (Map) c;
			campanhaIds.add(Long.valueOf(propertiesMap.get("id").toString()));
		});
		
		return campanhaIds;
	}

}
