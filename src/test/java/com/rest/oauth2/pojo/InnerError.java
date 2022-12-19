package com.rest.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InnerError {

	@JsonProperty("status")
	private Integer status;
	
	@JsonProperty("message")
	private String message;

}