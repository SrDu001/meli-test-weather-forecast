package com.srdu001.sswf.config;

import com.srdu001.sswf.domain.model.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public ResponseEntity<Object> error(NumberFormatException e) {
        return new ResponseEntity<>(ResponseObject.builder().code(400).description("The parameter must be a number").build(), HttpStatus.BAD_REQUEST);
    }

}