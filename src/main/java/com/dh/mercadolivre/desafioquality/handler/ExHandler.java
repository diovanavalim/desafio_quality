package com.dh.mercadolivre.desafioquality.handler;

import com.dh.mercadolivre.desafioquality.exceptions.ExceptionDetails;
import com.dh.mercadolivre.desafioquality.exceptions.NotFoundPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException
                                                                                               exception) {

        return new ResponseEntity<ExceptionDetails>(
                ExceptionDetails.builder()
                        .title("Method Argument Not Valid")
                        .message(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException
                                                                                               exception) {

        return new ResponseEntity<ExceptionDetails>(
                ExceptionDetails.builder()
                        .title("Method Argument Not Valid")
                        .message(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundPropertyException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundPropertyEx(NotFoundPropertyException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Property not Found")
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                .build(),
            HttpStatus.NOT_FOUND
        );
    }

}
