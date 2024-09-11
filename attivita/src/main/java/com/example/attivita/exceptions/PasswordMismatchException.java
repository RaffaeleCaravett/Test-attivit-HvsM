package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class PasswordMismatchException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public PasswordMismatchException(String message){
        this.message=message;
    }
    public  PasswordMismatchException(List<ObjectError> messageList){
        this.messageList=messageList;
    }
}
