package com.dh.mercadolivre.desafioquality.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class that extends of the RunTimeException error class. It represents the custom Exception ServerException.
 * @author Amanda Marinelli, Diovana Valim, Gabriela Azevedo, Rafael Cavalcante, Thiago Frozzi e Thiago Guimarães
 * @version 0.0.1
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends RuntimeException {

    /**
     * Constructor method called whether any server operation goes wrong.
     * @param message custom message displayed to user by the server response
     */
    public ServerException(String message) {
        super(message);
    }
}
