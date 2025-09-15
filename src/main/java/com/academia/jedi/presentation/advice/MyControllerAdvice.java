package com.academia.jedi.presentation.advice;

import com.academia.jedi.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(JediNotFoundException.class)
    public ResponseEntity<String> manageJediNotFound(JediNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PadawanNotFoundException.class)
    public ResponseEntity<String> managePadawanNotFound(PadawanNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullIDException.class)
    public ResponseEntity<String> manageNullIDException(NullIDException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadMasterRequest.class)
    public ResponseEntity<String> manageBadMasterRequest(BadMasterRequest ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadPadawanRequest.class)
    public ResponseEntity<String> manageBadPadawanRequest(BadPadawanRequest ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
