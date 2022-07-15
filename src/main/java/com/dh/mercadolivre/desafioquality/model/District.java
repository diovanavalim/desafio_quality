package com.dh.mercadolivre.desafioquality.model;

import lombok.*;

import javax.validation.constraints.*;

/**
 * Method implemented by Lombok lib to get access on District class private attributes
 */
@Getter

/**
 * Method implemented by Lombok lib to set District class private attributes
 */
@Setter

/**
 * Method implemented by Lombok lib to automatically produce the required code to instantiate District class
 */
@Builder

/**
 * Method Default Constructor implemented by Lombok lib
 */
@AllArgsConstructor

/**
 * Class for objects of District type that contains attributes
 * @author Diovana Valim
 * @version 0.0.1
 * @see javax.validation.constraints
 */
@NoArgsConstructor
public class District {
    @NotBlank(message = "O nome do bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do nome do bairro não pode exceder 45 caracteres.")
    private String name;

    @NotBlank(message = "O nome da cidade não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do nome da cidade não pode exceder 45 caracteres.")
    private String city;

    @NotNull(message = "O valor do metro quadrado não pode estar vazio.")
    @DecimalMax(value = "999999999999.99", message = "O valor do metro quadrado não pode ser maior que 999999999999.99.")
    private double valueDistrictM2;
}
