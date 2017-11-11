package br.com.rr.campanha.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rr.campanha.domain.Campanha;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RrCampanhaApplicationTests {
	
	@Autowired
	private TerminoVigenciaHelper helper;
	
	@Test
	public void exampleTest() throws ParseException {
		List<Campanha> campanhas = new ArrayList<>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Campanha c1 = new Campanha();
		c1.setId(1l); 
		c1.setInicioVigencia(dateFormat.parse("01/10/2017")); 
		c1.setTerminoVigencia(dateFormat.parse("02/10/2017"));
		
		Campanha c2 = new Campanha();
		c2.setId(2l); 
		c2.setInicioVigencia(dateFormat.parse("01/10/2017")); 
		c2.setTerminoVigencia(dateFormat.parse("03/10/2017"));
		
		campanhas.add(c1); campanhas.add(c2);
		
		Assert.assertTrue(helper != null);
		Map<Long, Date> mapIncrement = this.helper.incrementTermino(campanhas, dateFormat.parse("03/10/2017"));
	
		Assert.assertEquals(2, mapIncrement.size());
		
		Assert.assertEquals(dateFormat.parse("04/10/2017"), mapIncrement.get(1l));
		Assert.assertEquals(dateFormat.parse("05/10/2017"), mapIncrement.get(2l));
	}
	
	@Test
	public void withThreeCampanhasTest() throws ParseException {
		List<Campanha> campanhas = new ArrayList<>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Campanha c1 = new Campanha();
		c1.setId(1l); 
		c1.setInicioVigencia(dateFormat.parse("01/10/2017")); 
		c1.setTerminoVigencia(dateFormat.parse("02/10/2017"));
		
		Campanha c2 = new Campanha();
		c2.setId(2l); 
		c2.setInicioVigencia(dateFormat.parse("01/10/2017")); 
		c2.setTerminoVigencia(dateFormat.parse("03/10/2017"));
		
		Campanha c3 = new Campanha();
		c3.setId(3l); 
		c3.setInicioVigencia(dateFormat.parse("01/10/2017")); 
		c3.setTerminoVigencia(dateFormat.parse("04/10/2017"));
		
		campanhas.add(c1); campanhas.add(c2); campanhas.add(c3);
		
		Assert.assertTrue(helper != null);
		Map<Long, Date> mapIncrement = this.helper.incrementTermino(campanhas, dateFormat.parse("03/10/2017"));
	
		Assert.assertEquals(3, mapIncrement.size());
		
		Assert.assertEquals(dateFormat.parse("04/10/2017"), mapIncrement.get(1l));
		Assert.assertEquals(dateFormat.parse("05/10/2017"), mapIncrement.get(2l));
		Assert.assertEquals(dateFormat.parse("06/10/2017"), mapIncrement.get(3l));
	}
	
	@Test
	public void withBiggerIntervalTest() throws ParseException {
		List<Campanha> campanhas = new ArrayList<>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Campanha c1 = new Campanha();
		c1.setId(1l); 
		c1.setInicioVigencia(dateFormat.parse("01/10/2017")); 
		c1.setTerminoVigencia(dateFormat.parse("03/10/2017"));
		
		Campanha c2 = new Campanha();
		c2.setId(2l); 
		c2.setInicioVigencia(dateFormat.parse("01/10/2017")); 
		c2.setTerminoVigencia(dateFormat.parse("05/10/2017"));
		
		Campanha c3 = new Campanha();
		c3.setId(3l); 
		c3.setInicioVigencia(dateFormat.parse("01/10/2017")); 
		c3.setTerminoVigencia(dateFormat.parse("06/10/2017"));
		
		campanhas.add(c1); campanhas.add(c2); campanhas.add(c3);
		
		Assert.assertTrue(helper != null);
		Map<Long, Date> mapIncrement = this.helper.incrementTermino(campanhas, dateFormat.parse("05/10/2017"));
	
		Assert.assertEquals(3, mapIncrement.size());
		
		Assert.assertEquals(dateFormat.parse("04/10/2017"), mapIncrement.get(1l));
		Assert.assertEquals(dateFormat.parse("06/10/2017"), mapIncrement.get(2l));
		Assert.assertEquals(dateFormat.parse("07/10/2017"), mapIncrement.get(3l));
	}
	
}
