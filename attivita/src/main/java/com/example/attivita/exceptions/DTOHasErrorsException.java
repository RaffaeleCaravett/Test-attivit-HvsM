package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class DTOHasErrorsException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public DTOHasErrorsException (String message){
        this.message=message;
    }
    public DTOHasErrorsException (List<ObjectError> objectErrors){
        this.messageList=objectErrors;
    }
}
