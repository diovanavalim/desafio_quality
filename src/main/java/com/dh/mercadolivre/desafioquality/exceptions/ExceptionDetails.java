package com.dh.mercadolivre.desafioquality.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Class to customize the return of errors response.
 * @author Amanda Marinelli, Diovana Valim, Gabriela Azevedo, Rafael Cavalcante, Thiago Frozzi e Thiago Guimarães
 * @version 0.0.1
 */
@Data
@Builder
public class ExceptionDetails {
    private String title;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}