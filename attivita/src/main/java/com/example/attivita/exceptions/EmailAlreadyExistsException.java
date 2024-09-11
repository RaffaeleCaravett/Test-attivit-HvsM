package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class EmailAlreadyExistsException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public EmailAlreadyExistsException (String message){
        this.message=message;
    }
    public EmailAlreadyExistsException (List<ObjectError> errorList){
        this.messageList=errorList;
    }
}
