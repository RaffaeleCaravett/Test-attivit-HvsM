package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class AccessTokenInvalidException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public AccessTokenInvalidException(String message){
        this.message=message;
    }
    public AccessTokenInvalidException(List<ObjectError> messageList){
        this.messageList=messageList;
    }
}