package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class PrenotazioneAlreadyExistsException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public PrenotazioneAlreadyExistsException (String message){
        this.message=message;
    }
    public PrenotazioneAlreadyExistsException (List<ObjectError> errorList){
        this.messageList=errorList;
    }
}

