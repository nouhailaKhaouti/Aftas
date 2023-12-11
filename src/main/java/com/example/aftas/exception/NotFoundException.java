package com.example.aftas.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{
    public String getError() {
        return "Can't find the data your searching for";
    }
    public HttpStatus getCode(){
        return HttpStatus.NOT_FOUND;
    }
}
