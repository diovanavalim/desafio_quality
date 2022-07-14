package com.dh.mercadolivre.desafioquality.model;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
