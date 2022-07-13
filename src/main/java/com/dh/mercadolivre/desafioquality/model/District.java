package com.dh.mercadolivre.desafioquality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class District {
    @NotBlank(message = "O nome do bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do nome do bairro não pode exceder 45 caracteres.")
    private String name;

    @NotEmpty(message = "O valor do metro quadrado não pode estar vazio.")
    @DecimalMax(value = "999999999999.99", message = "O valor do metro quadrado não pode ser maior que 999999999999.99.")
    private double valueDistrictM2;
}
