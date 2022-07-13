package com.dh.mercadolivre.desafioquality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Property {
    
    private Long id;
    
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "([A-Z]{1}[a-z]+\\\\s??)+", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome da propriedade não pode exceder 30 caracteres.")
    private String propName;

    @Valid
    private District propDistrict;

    @NotEmpty(message = "A lista de cômodos não pode estar vazia.")
    private List<@Valid Room> roomList;
}
