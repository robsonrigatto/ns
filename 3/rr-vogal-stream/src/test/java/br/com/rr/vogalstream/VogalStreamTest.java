package br.com.rr.vogalstream;

import org.junit.Assert;
import org.junit.Test;

import br.com.rr.vogalstream.impl.VogalStream;

public class VogalStreamTest {
	
	@Test
	public void sampleTest() {
		VogalStream stream = new VogalStream("aAbBABacafe");
		Assert.assertTrue(stream.hasNext());
		Assert.assertEquals('a', stream.getNext());
		Assert.assertTrue(stream.hasNext());
		Assert.assertEquals('e', stream.getNext());
		Assert.assertFalse(stream.hasNext());
	}
	
	@Test
	public void noOcurrenceTest() {
		VogalStream stream = new VogalStream("effeffee");
		Assert.assertFalse(stream.hasNext());
	}
	
	@Test
	public void exactlyLengthTest() {
		VogalStream stream = new VogalStream("axe");
		Assert.assertTrue(stream.hasNext());
		Assert.assertEquals('e',stream.getNext());
		Assert.assertFalse(stream.hasNext());
	}
	
	@Test
	public void smallWordTest() {
		VogalStream stream = new VogalStream("ax");
		Assert.assertFalse(stream.hasNext());
		
	}

}
