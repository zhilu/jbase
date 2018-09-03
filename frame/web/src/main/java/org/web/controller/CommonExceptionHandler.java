package org.web.controller;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(value=UnauthenticatedException.class)
	public void handle(Exception e){
		System.out.println(e);
	}

}
