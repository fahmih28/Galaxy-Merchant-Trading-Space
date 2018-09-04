package org.propspace.roman;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;


public class RomanTranslatorTest {

	@Test
	public void initializeClass()
	{
		new RomanTranslator();
	}
	
	@Test
	public void qualifiedNameOfRomanEnum()
	{
		org.propspace.roman.RomanEnum romanX = org.propspace.roman.RomanEnum.X;
		Assert.assertNotNull(romanX);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTranslateNullParamThrowException() throws Exception
	{
		RomanTranslator.translate(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTranslateEmptyParamThrowException() throws Exception
	{
		RomanTranslator.translate(" ");
	}
	
	@Test(expected=RomanSymbolException.class)
	public void testTranslateIllegalRomanParamThrowException() throws Exception
	{
		RomanTranslator.translate("K");
	}
	
	@Test(expected=RomanSymbolException.class)
	public void testRepeatedIllegalRomanCharacterThrowException() throws Exception
	{
		RomanTranslator.translate("VV");
	}
	
	@Test(expected=RomanSymbolException.class)
	public void testIllegalFollowedBiggerRomanThrowException() throws Exception
	{
		RomanTranslator.translate("XXCIC");
	}
	
	@Test(expected=RomanSymbolException.class)
	public void testIllegalFollowedIllegalRomanThrowException() throws Exception
	{
		RomanTranslator.translate("XCIC");
	}
	
	@Test(expected=RomanSymbolException.class)
	public void testIllegalSubtractedRomanCharacterThrowException() throws Exception
	{
		RomanTranslator.translate("IC");
	}
	
	@Test(expected=RomanSymbolException.class)
	public void testIllegalRepeatedRomanCharacterThrowException() throws Exception
	{
		RomanTranslator.translate("MMMM");
	}
	
	@Test
	public void testTranslateShouldPrecedRomanBiggerThanNextRoman() throws Exception
	{
		BigDecimal ret = RomanTranslator.translate("CL");
		Assert.assertEquals(BigDecimal.valueOf(150l), ret);
	}
	
	@Test
	public void testTranslateShouldPrecedRomanLowerThanNextRoman() throws Exception
	{
		BigDecimal ret = RomanTranslator.translate("CMXL");
		Assert.assertEquals(BigDecimal.valueOf(940l), ret);
	}
}
