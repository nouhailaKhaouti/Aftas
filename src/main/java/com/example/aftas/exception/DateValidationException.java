package com.example.aftas.exception;

import org.springframework.http.HttpStatus;

public class DateValidationException extends RuntimeException{
    public String getError() {
        return "It seems like the date you've entered does not meet the validation criteria.";
    }
    public HttpStatus getCode(){
        return HttpStatus.BAD_REQUEST;
    }
}
