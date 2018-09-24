package com.coffee.task.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coffee.task.response.BaseResponse;
import com.coffee.task.response.MachineResponse;
import com.coffee.task.type.CoffeeType;
 

@RestController
public class CoffeeRestController {
 
	private CoffeeMachineState coffeeState;
	
    
    @GetMapping("/coffee/{_coffeeType}")
    public ResponseEntity<BaseResponse> getCoffee(@PathVariable String _coffeeType) {
 
    	BaseResponse 	response = new BaseResponse(); 
    	HttpStatus 		status 		= HttpStatus.OK;

    	try {
			CoffeeType coffeeType = CoffeeType.controller(_coffeeType);
			
			if (getCoffeeMachineState().checkForOneCup(coffeeType)) {
				getCoffeeMachineState().makeCup(coffeeType);
				response.setStatus("OK");
			}
			else {
				ArrayList<String> errorList = coffeeState.errorsCheckForOneCup(coffeeType);
				response.setStatus("ERROR");
				response.setErrorText(errorList);
				status = HttpStatus.NOT_FOUND;
			}	
		}
		catch(IllegalArgumentException _IllegalArgumentException) {
			response.setStatus("ERROR");
			ArrayList<String> errorList = new ArrayList<String>();
			errorList.add(String.format("%s not find", _coffeeType));
			response.setErrorText(errorList);
		}
    	
        return new ResponseEntity<BaseResponse>(response, status);
    }
    
    @PutMapping("/machine")
    public ResponseEntity<BaseResponse> addAttributs(@RequestParam(required=false) Double water, 
    												 @RequestParam(required=false) Double grain, 
    												 @RequestParam(required=false) Double milk){
    	
    	BaseResponse 	response 	= new BaseResponse(); 
    	HttpStatus 		status 		= HttpStatus.OK;
    	String 			statusString = "OK";
    	
    	

    	ArrayList<String> errorList = new ArrayList<String>();
    	
    	if (water != null && !getCoffeeMachineState().addWater(water)) {
    		errorList.add("Water volume exceeded");
    	}
    	
		if (grain != null && !getCoffeeMachineState().addGrains(grain)) {
			errorList.add("Grains volume exceeded");  		
		}

		if (milk != null && !getCoffeeMachineState().addMilk(milk)) {
			errorList.add("Milk volume exceeded");
		}
		
		if (errorList.size() > 0) {
			response.setErrorText(errorList);
			status = HttpStatus.NOT_ACCEPTABLE;
			statusString = "ERROR";
		}
		
		response.setStatus(statusString);

    	return new ResponseEntity<BaseResponse>(response, status);
    }
    
    @GetMapping("/machine")
    public ResponseEntity<MachineResponse> machineStatus(){
    	MachineResponse response 	= new MachineResponse(); 
    	HttpStatus 		status 		= HttpStatus.OK;

    	ArrayList<String> errorList = getCoffeeMachineState().getMachineErrors();

		if (errorList.size() > 0) {
			response.setErrorText(errorList);
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		
		response.setGrainVolume(getCoffeeMachineState().getGrainVolume());
		response.setWaterVolume(getCoffeeMachineState().getWaterVolume());
		response.setMilkVolume(getCoffeeMachineState().getMilkVolume());

    	return new ResponseEntity<MachineResponse>(response, status);
    }
    
    @PostMapping("/machine")
    public ResponseEntity<HttpStatus> cleanMachine(){

    	getCoffeeMachineState().cleanMachine();

    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    private  CoffeeMachineState getCoffeeMachineState() {
		
		if (coffeeState == null) {
			coffeeState = new CoffeeMachineState();
		}
		
		return coffeeState;
	}
}