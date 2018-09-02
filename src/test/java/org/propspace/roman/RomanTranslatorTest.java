package org.propspace.roman;

import org.junit.Test;

public class RomanTranslatorTest {

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
}
