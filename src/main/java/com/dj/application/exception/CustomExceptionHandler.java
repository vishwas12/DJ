package com.dj.application.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dj.dto.UiData;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({ CustomGenericException.class })
    public ResponseEntity<Object> handleGenericExcption(CustomGenericException e) {

        UiData apiError =
                new UiData(false,e.getExceptionMsg(),"ERROR");
        return new ResponseEntity<Object>(apiError, e.getExceptionCode());
    }

}
