package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class UnauthorizedException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public UnauthorizedException(String message){
        this.message=message;
    }
    public UnauthorizedException(List<ObjectError> messageList){
        this.messageList=messageList;
    }
}
