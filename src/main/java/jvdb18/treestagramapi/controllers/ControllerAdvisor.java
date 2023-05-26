package jvdb18.treestagramapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jvdb18.treestagramapi.exception.NoSuchUserException;
import jvdb18.treestagramapi.exception.UserAlreadyExistException;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(value
                      = NoSuchUserException.class)
    public @ResponseBody ErrorResponse
    handleNoSuchUserException(NoSuchUserException ex)
    {
        return new ErrorResponseException(HttpStatus.BAD_REQUEST, ex);
    }
    
    @ExceptionHandler(value
                      = UserAlreadyExistException.class)
    public @ResponseBody ErrorResponse
    handleUserAlreadyExistException(UserAlreadyExistException ex)
    {
        return new ErrorResponseException(HttpStatus.BAD_REQUEST, ex);
    }
}

