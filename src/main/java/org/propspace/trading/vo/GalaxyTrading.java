package org.propspace.trading.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GalaxyTrading {
	private Map<String,String> romanCode;
	private Map<String,Unit> items;
	public GalaxyTrading()
	{
		romanCode = new HashMap<String,String>();
		items = new HashMap<String,Unit>();
	}
	
	
	public void setRomanCode(String key,String value)
	{
		romanCode.put(key, value);
	}
	
	public void setItem(String itemName,BigDecimal value,String unitName)
	{
		items.put(itemName, new Unit(unitName, value));
	}
	
	public String getRomanCode(String key)
	{
		return romanCode.get(key);
	}
	
	public Unit getItem(String itemName)
	{
		return items.get(itemName);
	}
	
}
