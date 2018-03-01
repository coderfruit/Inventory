package com.stee.inventory.entity.sel;

/**
 * 
 * @pSTL_SEL
 * @DataType.java
 * @author yuxiaolin
 * @2018年2月23日
 */
public enum DataType {

	EnergyUsage(1),
	BurningHour(2),
	Current(3),
	Voltage(4),
	ActivePower(5),
	ReactivePower(6),
	PowerFactor(7),
	Temperature(8),
	ApparentPower(9);


	private Integer index;
	
	private DataType(Integer index) {  
		this.index = index;  
	}
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	
	
}
