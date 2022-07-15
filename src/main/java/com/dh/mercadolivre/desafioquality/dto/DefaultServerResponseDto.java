package com.dh.mercadolivre.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Method Getter implemented by Lombok lib for get access the private attributes of the Product Class
 */
@Getter
/**
 * Method Setter implemented by Lombok lib for set the private attributes of the Product Class
 */
@Setter
/**
 * Method Default Constructor implemented by Lombok lib
 */
@NoArgsConstructor

/**
 *class for a data tranfers Object for a message response
 * @author Gabriela Azevedo, Amanda Marinelli
 * @version 0.0.1
 * @see java.lang.Object
 */
public class DefaultServerResponseDto {
    private String message;
    private String status;

    /**
     * method for constructing an response Dto
     * @param message, a String whit teh error message
     * @param status, a String with the status of the response
     */
    public DefaultServerResponseDto(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
