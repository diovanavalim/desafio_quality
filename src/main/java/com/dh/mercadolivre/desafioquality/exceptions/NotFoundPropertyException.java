package com.dh.mercadolivre.desafioquality.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundProperty extends RuntimeException {

    public NotFoundProperty(String message) {
        super(message);
    }
}
