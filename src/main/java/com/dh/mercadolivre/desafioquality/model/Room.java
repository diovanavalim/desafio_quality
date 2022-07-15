package com.dh.mercadolivre.desafioquality.model;

import lombok.*;

import javax.validation.constraints.*;

/**
 * Method implemented by Lombok lib to get access on Room class private attributes
 */
@Getter

/**
 * Method implemented by Lombok lib to set Room class private attributes
 */
@Setter

/**
 * Method implemented by Lombok lib to automatically produce the required code to instantiate Room class
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
public class Room {
    @NotBlank(message = "O campo não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    @Pattern(regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ]+", message = "O nome do cômodo deve começar com uma letra maiúscula.")
    private String roomName;

    @NotNull(message = "A largura do cômodo não pode estar vazia.")
    @DecimalMax(value = "25", message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double roomWidth;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio.")
    @DecimalMax(value = "33", message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    private double roomLength;

}
