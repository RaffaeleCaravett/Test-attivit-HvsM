package com.example.attivita.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

    @Getter
    @Setter
    public class CategoriaNotFoundException extends RuntimeException{
        private String message;
        private List<ObjectError> messageList;

        public CategoriaNotFoundException(String message){
            this.message=message;
        }
        public  CategoriaNotFoundException(List<ObjectError> messageList){
            this.messageList=messageList;
        }
    }

