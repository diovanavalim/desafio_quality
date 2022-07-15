package com.dh.mercadolivre.desafioquality.handler;

import com.dh.mercadolivre.desafioquality.exceptions.DistrictNotFoundException;
import com.dh.mercadolivre.desafioquality.exceptions.ExceptionDetails;
import com.dh.mercadolivre.desafioquality.exceptions.PropertyNotFoundException;
import com.dh.mercadolivre.desafioquality.exceptions.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Class that handle the exceptions that are thrown over the code execution flow.
 * @author Diovana Valim, Gabriela Azevedo
 * @version 0.0.1
 */

@ControllerAdvice
public class ExHandler {

    /**
     * Method that captures a MethodArgumentNotValidException and build a response to send through HTTP request.
     * @param exception instance of MethodArgumentNotValidException class captured during the code execution flow.
     * @return a ResponseEntity containing details of the exception and a compatible HTTP status code.
     */
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

    /**
     * Method that captures a HttpMessageNotReadableException and build a response to send through HTTP request.
     * @param exception instance of HttpMessageNotReadableException class captured during the code execution flow.
     * @return a ResponseEntity containing details of the exception and a compatible HTTP status code.
     */
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

    /**
     * Method that captures a ServerException and build a response to send through HTTP request.
     * @param exception instance of ServerException class captured during the code execution flow.
     * @return a ResponseEntity containing details of the exception and a compatible HTTP status code.
     */
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ExceptionDetails> serverExceptionHandler(ServerException exception) {

        return new ResponseEntity<ExceptionDetails>(
                ExceptionDetails.builder()
                        .title("Internal Server Error")
                        .message(exception.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method that captures a PropertyNotFoundException and build a response to send through HTTP request.
     * @param exception instance of NotFoundException class captured during the code execution flow.
     * @return a ResponseEntity containing details of the exception and a compatible HTTP status code.
     */
    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ExceptionDetails> propertyNotFoundHandler(PropertyNotFoundException exception) {

        return new ResponseEntity<ExceptionDetails>(
                ExceptionDetails.builder()
                        .title("Property Not Found")
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DistrictNotFoundException.class)
    public ResponseEntity<ExceptionDetails> districtNotFoundHandler(DistrictNotFoundException exception) {

        return new ResponseEntity<ExceptionDetails>(
                ExceptionDetails.builder()
                        .title("District Not Found")
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
