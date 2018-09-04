package org.propspace.roman;

import org.junit.Assert;
import org.junit.Test;



public class RomanSymbolExceptionTest {

	@Test
	public void testGetSysmbol()
	{
		RomanSymbolException ex = new RomanSymbolException("Illegal Roman Characte", 'z');
		Assert.assertEquals('z', ex.getSymbol());
	}
}
