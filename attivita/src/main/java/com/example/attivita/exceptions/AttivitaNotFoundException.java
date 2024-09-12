package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class AttivitaNotFoundException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public AttivitaNotFoundException(String message){
        this.message=message;
    }
    public  AttivitaNotFoundException(List<ObjectError> messageList){
        this.messageList=messageList;
    }
}
