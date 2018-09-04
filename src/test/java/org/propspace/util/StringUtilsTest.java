package org.propspace.util;

import org.junit.Assert;
import org.junit.Test;


public class StringUtilsTest {
	
	@Test
	public void initializeStringUtils()
	{
		new StringUtils();
	}
	@Test
	public void isInCharshouldReturnTrue()
	{
		char c = 'i';
		Assert.assertTrue(StringUtils.isIn(c,'a','i'));
	}
	
	@Test
	public void isInCharShouldReturnFalse()
	{
		char c = 'k';
		Assert.assertFalse(StringUtils.isIn(c,'a','i'));
	}
	
	@Test
	public void isInStringShouldReturnFalse()
	{
		String c = "k";
		Assert.assertFalse(StringUtils.isInIgnoreCase(c,"q","l"));
	}
	
	@Test
	public void isInStringShouldReturnTrue()
	{
		String c = "k";
		Assert.assertTrue(StringUtils.isInIgnoreCase(c,"q","k"));
	}
}
