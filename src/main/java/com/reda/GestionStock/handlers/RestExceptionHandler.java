package com.reda.GestionStock.handlers;

import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleExceptionNotFound(EntityNotFoundException exception, WebRequest webRequest){

        final HttpStatus notFound = HttpStatus.NOT_FOUND; //

        final ErrorDto errorDto = ErrorDto.builder()
                .errorCodes(exception.getErrorCodes())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleExceptionInvalid(InvalidEntityException exception, WebRequest webRequest){
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .errorCodes(exception.getErrorCodes())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errorsList(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }

}
