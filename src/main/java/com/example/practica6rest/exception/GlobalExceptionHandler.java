package com.example.practica6rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<String> handleException(Exception ex) {
////@TODO
//        return new ResponseEntity<String>(
//                ex.getMessage(),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}