package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class BadRequestException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public BadRequestException (String message){
        this.message=message;
    }
    public BadRequestException (List<ObjectError> objectErrors){
        this.messageList=objectErrors;
    }
}
