package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class CategoriaAlreadyExistsException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public CategoriaAlreadyExistsException (String message){
        this.message=message;
    }
    public CategoriaAlreadyExistsException (List<ObjectError> errorList){
        this.messageList=errorList;
    }
}
