package br.com.rr.campanha.helper;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.rr.campanha.domain.Campanha;
import br.com.rr.campanha.utils.LocalDateUtils;

@Component
public class TerminoVigenciaHelper {
	
	public Map<Long, Date> incrementTermino(List<Campanha> campanhas, Date newTerminoVigencia) {
		Map<Long, Date> terminoMap = new HashMap<>();
		LocalDate newTerminoLD = LocalDateUtils.dateToLocalDate(newTerminoVigencia);
		LocalDate lastLD = null;
		
		for(Campanha c : campanhas) {
			LocalDate cTerminoLD = LocalDateUtils.dateToLocalDate(c.getTerminoVigencia());
			
			if(cTerminoLD.isBefore(newTerminoLD.minusDays(1))) { //OK
				cTerminoLD = cTerminoLD.plusDays(1);
			
			} else if (cTerminoLD.isEqual(newTerminoLD.minusDays(1))) { //OK
				cTerminoLD = cTerminoLD.plusDays(2);
			
			} else if (cTerminoLD.isEqual(newTerminoLD) || cTerminoLD.isAfter(newTerminoLD)) {
				if(cTerminoLD.isEqual(newTerminoLD)) {
					cTerminoLD = cTerminoLD.plusDays(1); //OK
				}
				
				if(lastLD != null && (lastLD.isEqual(cTerminoLD) || lastLD.isAfter(cTerminoLD))) {
					
					if(lastLD.isEqual(cTerminoLD)) {
						cTerminoLD = cTerminoLD.plusDays(1);
					} else {
						cTerminoLD = lastLD.plusDays(1);
					}

				}
			}
			
			terminoMap.put(c.getId(), LocalDateUtils.localDateToDate(cTerminoLD));
			lastLD = cTerminoLD;
		}
		
		return terminoMap;
	}

}
