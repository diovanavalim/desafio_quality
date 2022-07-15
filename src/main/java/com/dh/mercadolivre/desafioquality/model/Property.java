package com.dh.mercadolivre.desafioquality.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * Method implemented by Lombok lib to get access on Property class private attributes
 */
@Getter

/**
 * Method implemented by Lombok lib to set Property class private attributes
 */
@Setter

/**
 * Method implemented by Lombok lib to automatically produce the required code to instantiate Property class
 */
@Builder

/**
 * Method Default Constructor implemented by Lombok lib
 */
@AllArgsConstructor

/**
 * Method Constructor of the Constructor Class
 */
@NoArgsConstructor
public class Property {
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "(?=^.{2,60}$)^[A-ZÀÁÂĖÈÉÊÌÍÒÓÔÕÙÚÛÇ][a-zàáâãèéêìíóôõùúç]+(?:[ ](?:das?|dos?|de|e|[A-Z][a-z]+))*$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome da propriedade não pode exceder 30 caracteres.")
    private String propName;

    @Valid
    private District propDistrict;

    @NotEmpty(message = "A lista de cômodos não pode estar vazia.")
    private List<@Valid Room> roomList;

    private long id;
}
