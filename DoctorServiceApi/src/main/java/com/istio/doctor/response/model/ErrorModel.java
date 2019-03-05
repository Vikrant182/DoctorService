package com.istio.doctor.response.model;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorModel {
	
	private String errorMessage;
	private HttpStatus statusCode;

}
