package br.com.rr.sociotorcedor.integration;

import java.util.List;

import br.com.rr.sociotorcedor.enumeration.Time;
import br.com.rr.sociotorcedor.exception.IntegrationException;

public interface CampanhaIntegration {

	List<Long> findByTime(Time time) throws IntegrationException;
}
