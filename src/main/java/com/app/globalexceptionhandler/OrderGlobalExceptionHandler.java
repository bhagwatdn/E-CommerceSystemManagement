package com.app.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderGlobalExceptionHandler {
	
	@ExceptionHandler(value=NoSuchOrderExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
     public @ResponseBody ErrorResponse orderHandleException(NoSuchOrderExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	 
 }
	@ExceptionHandler(value = OrderAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse orderHandlerException(OrderAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	
}
	@ExceptionHandler(value = OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse orderHandlerException2(OrderNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	
}
}
