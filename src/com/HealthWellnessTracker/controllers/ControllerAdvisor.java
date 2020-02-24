package com.HealthWellnessTracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {
	
   @ExceptionHandler({NoHandlerFoundException.class})
   @ResponseStatus(value = HttpStatus.NOT_FOUND)
   public String handleNotFound(Exception ex) {
	  return "errorpage404";
   }

}
