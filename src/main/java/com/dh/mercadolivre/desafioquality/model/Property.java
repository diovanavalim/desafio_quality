package com.dh.mercadolivre.desafioquality.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
