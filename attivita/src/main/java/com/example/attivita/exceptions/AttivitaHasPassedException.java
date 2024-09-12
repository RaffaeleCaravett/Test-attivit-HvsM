package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class AttivitaHasPassedException extends RuntimeException{
    private String message;
    private List<ObjectError> messageList;

    public AttivitaHasPassedException(String message){
        this.message=message;
    }
    public AttivitaHasPassedException(List<ObjectError> messageList){
        this.messageList=messageList;
    }
}
