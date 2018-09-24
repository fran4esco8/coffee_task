package com.coffee.task.controller;

import java.util.ArrayList;

import com.coffee.task.type.CoffeeType;

public class CoffeeMachineState {
	
	private final static Double waterVolumeMax 	= 2.0;
	private final static Double milkVolumeMax 	= 1.0;
	private final static Double grainsVolumeMax = 0.1;
	private final static int qtyCupsMax = 8;
	private final static int roundDouble = 4;
	
	private Double waterVolumeCurrent 	= 0.0;
	private Double milkVolumeCurrent 	= 0.0;
	private Double grainsVolumeCurrent 	= 0.0;
	
	private int qtyCupsUsed = 0;
	
	public void cleanMachine() {
		
		waterVolumeCurrent 	= 0.0;
		milkVolumeCurrent 	= 0.0;
		grainsVolumeCurrent = 0.0;
		qtyCupsUsed 		= 0;
	}
	
	public ArrayList<String> errorsCheckForOneCup(CoffeeType _coffeeType) {
		
		ArrayList<String> errorList = new ArrayList<String>();
		
		if (waterVolumeCurrent - _coffeeType.getWaterVolumeUse() < 0) {
			errorList.add("NeedMoreWater");
		}
		
		if (milkVolumeCurrent - _coffeeType.getMilkVolumeUse() < 0) {
			errorList.add("NeedMoreMilk");
		}
		
		if (grainsVolumeCurrent - _coffeeType.getGrainsVolumeUse() < 0) {
			errorList.add("NeedMoreGrain");
		}
		
		if (qtyCupsUsed + 1 >= qtyCupsMax) {
			errorList.add("NeedCleanMachine.");
		}
		
		return errorList;
	}
	
	public ArrayList<String> getMachineErrors(){
		
		ArrayList<String> errorList = new ArrayList<String>();
    	
    	if (waterVolumeCurrent == 0) {
    		errorList.add("Water volume needed");
    	}
    	
		if (grainsVolumeCurrent == 0) {
			errorList.add("Grains volume needed");  		
		}

		if (milkVolumeCurrent == 0) {
			errorList.add("Milk volume needed");
		}
		
		if (qtyCupsUsed >= qtyCupsMax) {
			errorList.add("Clean machine");
		}
		
		return errorList;		
	}
	
	
	
	public boolean checkForOneCup(CoffeeType _coffeeType) {
		
		return  waterVolumeCurrent  - _coffeeType.getWaterVolumeUse() 	>= 0 && 
				milkVolumeCurrent 	- _coffeeType.getMilkVolumeUse() 	>= 0 && 
				grainsVolumeCurrent - _coffeeType.getGrainsVolumeUse() 	>= 0 &&
				qtyCupsUsed + 1 < qtyCupsMax;
	}
	
	public boolean  makeCup(CoffeeType _coffeeType) {

		if (!checkForOneCup(_coffeeType)) {
			return false;
		}

		waterVolumeCurrent 	= round(waterVolumeCurrent 	- _coffeeType.getWaterVolumeUse(), 	roundDouble) ;
		milkVolumeCurrent 	= round(milkVolumeCurrent 	- _coffeeType.getMilkVolumeUse(), 	roundDouble);
		grainsVolumeCurrent = round(grainsVolumeCurrent - _coffeeType.getGrainsVolumeUse(), roundDouble);
		
		qtyCupsUsed 		+= 1;
		
		return true;
	}
	
	public boolean addWater(Double addWaterVolume) {
		
		if (waterVolumeCurrent + addWaterVolume > CoffeeMachineState.waterVolumeMax) {
			return false;
		}	
		
		waterVolumeCurrent += addWaterVolume;
		
		return true;
	}
	
	public boolean addMilk(Double addMilkVolume) {
		
		if (milkVolumeCurrent + addMilkVolume > CoffeeMachineState.milkVolumeMax) {
			return false;
		}		
		
		milkVolumeCurrent += addMilkVolume;
		
		return true;
	}

	public boolean addGrains(Double addGrainsVlume) {
	
		if (grainsVolumeCurrent + addGrainsVlume > CoffeeMachineState.grainsVolumeMax) {
			return false;
		}		
		
		grainsVolumeCurrent += addGrainsVlume;
		
		return true;
	}
	
	public Double getWaterVolume() {
		return waterVolumeCurrent;
	}
	
	public Double getMilkVolume() {
		return milkVolumeCurrent;
	}
	
	public Double getGrainVolume() {
		return grainsVolumeCurrent;
	}
		
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
