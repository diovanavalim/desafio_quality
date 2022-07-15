package com.dh.mercadolivre.desafioquality.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class that extends of the RunTimeException error class. It represents the custom Exception PropertyNotFound.
 * @author Diovana Valim, Gabriela Azevedo
 * @version 0.0.1
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PropertyNotFoundException extends RuntimeException {

    /**
     * Constructor method called whether an readFile operation for Property class has no results.
     * @param message custom message displayed to user by the server response
     */
    public PropertyNotFoundException(String message) {
        super(message);
    }
}
