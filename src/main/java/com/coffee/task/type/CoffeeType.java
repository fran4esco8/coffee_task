package com.coffee.task.type;

enum CoffeeTypeEnum{
	   capuchino, americano;}

public abstract class CoffeeType {
	
	protected static Double waterVolumeUse;
	protected static Double milkVolumeUse;
	protected static Double grainsVolumeUse;
	
	public Double getWaterVolumeUse() {
		return waterVolumeUse;
	}

	public Double getMilkVolumeUse() {
		return milkVolumeUse;
	}

	public Double getGrainsVolumeUse() {
		return grainsVolumeUse;
	}
	
	
	public static CoffeeType controller(String _coffeeType) {
		
		CoffeeType coffeeType;
		
		CoffeeTypeEnum type = CoffeeTypeEnum.valueOf(_coffeeType);
		
		switch(type) {
			case capuchino: coffeeType = new CapuchinoCoffeeType();
				break;
				
			case americano: coffeeType = new AmericanoCoffeeType();
				break;	
				
			default: coffeeType = null;
            	break;	
		}
		
		return coffeeType;
	}
}
