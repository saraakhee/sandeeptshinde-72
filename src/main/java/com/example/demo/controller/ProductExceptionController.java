package com.example.demo.controller;

import com.example.demo.exception.ProductNotfoundException;
import com.example.demo.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler(ProductNotfoundException.class)
    public ResponseEntity<Object> exception(ProductNotfoundException pe){
        ErrorMessage error = new ErrorMessage(
                "RESOURCE_NOT_FOUND",
                pe.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
