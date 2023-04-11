package jvdb18.treestagramapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jvdb18.treestagramapi.utils.ErrorDTO;

@ControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handle(Throwable e){
        if(e instanceof UsernameNotFoundException){
            return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(new ErrorDTO(e.getMessage()));
                   
        } 
        return ResponseEntity
               .status(HttpStatus.NOT_IMPLEMENTED)
               .build();
    }
}
