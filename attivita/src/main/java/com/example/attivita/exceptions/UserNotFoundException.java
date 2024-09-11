package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public UserNotFoundException (String message){
        this.message=message;
    }
    public UserNotFoundException (List<ObjectError> errorList){
        this.messageList=errorList;
    }
}
