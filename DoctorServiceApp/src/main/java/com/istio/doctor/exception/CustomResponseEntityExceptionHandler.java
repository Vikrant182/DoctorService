package com.istio.doctor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.istio.doctor.response.model.ErrorModel;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value 
		      = {DocNotFoundException.class })
		    protected ErrorModel handleRuntimeException(
		      RuntimeException ex, WebRequest request) {
		        ErrorModel model = new ErrorModel();
		        model.setErrorMessage(ex.getMessage());
		        model.setStatusCode(HttpStatus.NOT_FOUND);
		        return model;
		    }
}
