package org.propspace.util;

public class StringUtils {
	public static boolean isIn(char comparator,char... items)
	{
		for(char item:items )
		{
			if(item == comparator)
				return true;
		}
		return false;
	}
	
	public static boolean isInIgnoreCase(String comparator,String... items)
	{
		for(String item:items )
		{
			if(item.equalsIgnoreCase(comparator))
				return true;
		}
		return false;
	}
}
