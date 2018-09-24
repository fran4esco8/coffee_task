package com.coffee.task.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MachineResponse {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> errorText;
	
	private Double waterVolume;
	private Double grainVolume;
	private Double milkVolume;
	
	public List<String> getErrorText() {
		  return errorText;
		}
	public void setErrorText(List<String> errorText) {
	  this.errorText = errorText;
	}
	
	
	public Double getWaterVolume() {
	  return waterVolume;
	}
	
	public void setWaterVolume(Double waterVolume) {
	  this.waterVolume = waterVolume;
	}
	
	public Double getGrainVolume() {
	  return grainVolume;
	}
	
	public void setGrainVolume(Double grainVolume) {
	  this.grainVolume = grainVolume;
	}
	
	public Double getMilkVolume() {
	  return milkVolume;
	}
	
	public void setMilkVolume(Double milkVolume) {
	  this.milkVolume = milkVolume;
	}
}
