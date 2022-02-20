package com.example.SpringbootCrud.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
//Assignment Bonus2 - Add More Features - Customized Exception Handling,Custom Validation Errors handling

@ControllerAdvice
public class CustomizedExceptionHandler {
    //handling UserNotFound Exception
    @ExceptionHandler(UserNotFoundException.class)
     public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception, WebRequest request){
         ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
         return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
     }
    //handling GlobalExceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //handling custom validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),"Validation Error",exception.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
