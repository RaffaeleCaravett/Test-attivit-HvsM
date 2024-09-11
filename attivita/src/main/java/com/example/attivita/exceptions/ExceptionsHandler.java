package com.example.attivita.exceptions;

import com.example.attivita.payloads.errors.ErrorsDTO;
import com.example.attivita.payloads.errors.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsWithListDTO handleBadRequest(BadRequestException e) {
        if (e.getMessageList() != null) {
            List<String> errorsList = e.getMessageList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }

    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsWithListDTO handleUnauthorized(UnauthorizedException e) {
         if (e.getMessageList() != null) {
            List<String> errorsList = e.getMessageList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsWithListDTO handleAccessDenied(AccessDeniedException e) {
         if (e.getMessageList() != null) {
            List<String> errorsList = e.getMessageList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ErrorsWithListDTO handleNotFound(UserNotFoundException e) {
         if (e.getMessageList() != null) {
            List<String> errorsList = e.getMessageList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }
    @ExceptionHandler(AccessTokenInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsWithListDTO handleInvalidToken(AccessTokenInvalidException e) {
         if (e.getMessageList() != null) {
            List<String> errorsList = e.getMessageList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }
    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)  // 404
    public ErrorsWithListDTO handlePasswordMismatch(PasswordMismatchException e) {
         if (e.getMessageList() != null) {
            List<String> errorsList = e.getMessageList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)  // 404
    public ErrorsWithListDTO handleEmailExists(EmailAlreadyExistsException e) {
        if (e.getMessageList() != null) {
            List<String> errorsList = e.getMessageList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }

    }
    @ExceptionHandler(DTOHasErrorsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)  // 404
    public ErrorsWithListDTO handleDtoHasErrors(DTOHasErrorsException e) {
        if (e.getMessageList() != null) {
            List<String> errorsList = e.getMessageList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }

    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    public ErrorsDTO handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsDTO("Problema lato server...giuro che lo risolveremo presto", new Date());
    }
}