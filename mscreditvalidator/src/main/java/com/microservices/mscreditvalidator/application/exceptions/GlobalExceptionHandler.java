package com.microservices.mscreditvalidator.application.exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> feignClientException(FeignException.FeignClientException ex) {
        int status = ex.status();
        if(status == HttpStatus.NOT_FOUND.value()) {
            return new ResponseEntity<>("Client data not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.valueOf(status));
    }
}
