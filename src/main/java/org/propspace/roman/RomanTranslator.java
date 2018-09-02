package org.propspace.roman;

import java.math.BigDecimal;

import org.propspace.util.StringUtils;

public class RomanTranslator {
	private static final char I_ROMAN = 'I';
	private static final char V_ROMAN = 'V';
	private static final char X_ROMAN = 'X';
	private static final char L_ROMAN = 'L';
	private static final char C_ROMAN = 'C';
	private static final char D_ROMAN = 'D';
	private static final char M_ROMAN = 'M';
	
	

	public static BigDecimal translate(String roman) throws RomanSymbolException {
		if (roman == null)
			throw new IllegalArgumentException("roman argument must not null");

		if (roman.trim().isEmpty())
			throw new IllegalArgumentException("roman argument must not be empty");

		char[] chars = roman.toCharArray();
		int happen = 0;
		BigDecimal total = BigDecimal.ZERO;
		RomanEnum precedValue = null;
		RomanEnum currentValue = null;
		RomanEnum boundaryValue = null;
		for (int i = 0; i < chars.length; i++) {

			currentValue = RomanEnum.valueOf(chars[i]);
			if (currentValue == null) {
				throw new RomanSymbolException(String.format("Illegal Roman Symbol At '%d'", i), chars[i]);
			}
			
			checkFollowedAllowed(boundaryValue,currentValue);
			
			if (precedValue == null) {
				precedValue = currentValue;
				happen = 1;
			} else {
				if (currentValue.getSymbol() == precedValue.getSymbol()) {
					if (StringUtils.isIn(chars[i], V_ROMAN, L_ROMAN, D_ROMAN)) {
						throw new RomanSymbolException("Repeated Illegal Roman Symbol", chars[i]);
					}

					if (happen == 3) {
						throw new RomanSymbolException(
								String.format("Repeated More Than 3 Times From %d Until %d", i - 3, i), chars[i]);
					}
					
					happen++;
					total = total.add(BigDecimal.valueOf(precedValue.getValue()));
					precedValue = currentValue;

				} else {
					if (currentValue.getValue() > precedValue.getValue()) {
						if(!isSubtractedAllowed(precedValue, currentValue))
						{
							throw new RomanSymbolException(String.format("Roman Symbol '%c' Cannot Be Subtracted From '%c'", precedValue.getSymbol(),currentValue.getSymbol())
									, chars[i]);
						}
						
						if(happen > 1)
						{
							throw new RomanSymbolException(String.format("Roman Symbol '%c' Has Repeated And Followed By Bigger Value Of Symbol '%c'", precedValue.getSymbol(),currentValue.getSymbol()),currentValue.getSymbol());
						}
						
						total = total.add(BigDecimal.valueOf(currentValue.getValue() - precedValue.getValue()));
						boundaryValue = precedValue;
						precedValue = null;
					} else {
						total = total.add(BigDecimal.valueOf(precedValue.getValue()));
						precedValue = currentValue;
					}
					happen = 1;
				}
			}
		}

		if(precedValue != null) {
			total = total.add(BigDecimal.valueOf(precedValue.getValue()));
		}
		return total;
	}
	
	public static void checkFollowedAllowed(RomanEnum boundaryValue,RomanEnum currentValue) throws RomanSymbolException
	{
		 if(!(boundaryValue == null || currentValue.getValue() < boundaryValue.getValue())) {
			 throw new RomanSymbolException(String.format("Illegal Input For Boundary '%c'",boundaryValue.getSymbol()), currentValue.getSymbol());
		 }
	}
	
	private static boolean isSubtractedAllowed(RomanEnum preceed,RomanEnum nextRoman)
	{
		if(preceed.getSymbol() == I_ROMAN && StringUtils.isIn(nextRoman.getSymbol(), V_ROMAN,X_ROMAN))
			return true;
		else if(preceed.getSymbol() == X_ROMAN && StringUtils.isIn(nextRoman.getSymbol(),L_ROMAN,C_ROMAN))
			return true;
		else if(preceed.getSymbol() == C_ROMAN && StringUtils.isIn(nextRoman.getSymbol(),D_ROMAN,M_ROMAN))
			return true;
		return false;
	}
}
