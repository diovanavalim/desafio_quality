package com.dh.mercadolivre.desafioquality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Room {
    @NotBlank(message = "O campo não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    @Pattern(regexp = "([A-Z]{1}[a-z]+\\\\s??)+", message = "O nome do cômodo deve começar com uma letra maiúscula.")
    private String roomName;

    @NotEmpty(message = "A largura do cômodo não pode estar vazia.")
    @DecimalMax(value = "25", message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double roomWidth;

    @NotEmpty(message = "O comprimento do cômodo não pode estar vazio.")
    @DecimalMax(value = "33", message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    private double roomLength;

}
