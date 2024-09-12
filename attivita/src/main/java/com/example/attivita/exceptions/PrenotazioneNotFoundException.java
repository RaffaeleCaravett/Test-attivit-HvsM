package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class PrenotazioneNotFoundException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public PrenotazioneNotFoundException (String message){
        this.message=message;
    }
    public PrenotazioneNotFoundException (List<ObjectError> errorList){
        this.messageList=errorList;
    }
}

