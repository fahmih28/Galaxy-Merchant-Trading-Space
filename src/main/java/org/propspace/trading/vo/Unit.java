package org.propspace.trading.vo;

import java.math.BigDecimal;

public class Unit {
	private String name;
	private BigDecimal value;

	public Unit(String name, BigDecimal value) {
		this.name = name;
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getName() {
		return name;
	}


}
