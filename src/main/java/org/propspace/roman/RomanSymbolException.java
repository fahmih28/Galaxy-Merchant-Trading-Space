package org.propspace.roman;

public class RomanSymbolException extends Exception{
	private static final long serialVersionUID = -4069840863214224307L;
	private char symbol;
	public RomanSymbolException(String message,char symbol)
	{
		super(message);
		this.symbol = symbol;
	}
	
	public char getSymbol()
	{
		return symbol;
	}
}
