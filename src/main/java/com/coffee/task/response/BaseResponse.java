package com.coffee.task.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> errorText;

	private String status;
	
	public String getStatus() {
	  return status;
	}
	public void setStatus(String status) {
	  this.status = status;
	}
	
	public List<String> getErrorText() {
	  return errorText;
	}
	public void setErrorText(List<String> errorText) {
	  this.errorText = errorText;
	}
}