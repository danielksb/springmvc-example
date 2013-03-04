package example.springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3561865148620473568L;
	
	private String message = "";
	
	public ResourceNotFoundException() {
		
	}
	
	public ResourceNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
