package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class AccessDeniedException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public AccessDeniedException(String message){
        this.message=message;
    }
    public  AccessDeniedException(List<ObjectError> messageList){
        this.messageList=messageList;
    }
}
