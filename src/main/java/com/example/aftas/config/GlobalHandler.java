package com.example.aftas.config;


import com.example.aftas.exception.AlreadyExistException;
import com.example.aftas.exception.CustomException;
import com.example.aftas.exception.DateValidationException;
import com.example.aftas.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice

public class GlobalHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("error", errors);
        return errorResponse;
    }

    @ExceptionHandler(value= NotFoundException.class)
    public ResponseEntity<?> handleNotFoundErrors(NotFoundException e){
        return new ResponseEntity<>(e.getError(), e.getCode());
    }

    @ExceptionHandler(value= AlreadyExistException.class)
    public ResponseEntity<?> handleAlreadyExistErrors(AlreadyExistException e){
        return new ResponseEntity<>(e.getError(), e.getCode());
    }

    @ExceptionHandler(value= CustomException.class)
    public ResponseEntity<?> handleCustomErrors(CustomException e){
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

    @ExceptionHandler(value= DateValidationException.class)
    public ResponseEntity<?> handleDateValidationErrors(DateValidationException e){
        return new ResponseEntity<>(e.getError(), e.getCode());
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<?> handleLockedError(LockedException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
