package org.propspace.roman;

public enum RomanEnum {
	I(1,'I'),V(5,'V'),X(10,'X'),L(50,'L'),C(100,'C'),D(500,'D'),M(1000,'M');
	private int value;
	private char symbol;
	private RomanEnum(int value,char symbol)
	{
		this.value = value;
		this.symbol = symbol;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public char getSymbol()
	{
		return symbol;
	}
	
	public static RomanEnum valueOf(char c)
	{
		for(RomanEnum value:values())
		{
			if(c == value.name().charAt(0)) {
				return value;
			}
		}
		
		return null;
	}
}
