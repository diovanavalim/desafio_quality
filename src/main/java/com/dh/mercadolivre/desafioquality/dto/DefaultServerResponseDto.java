package com.dh.mercadolivre.desafioquality.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DefaultServerResponseDto {
    private String message;
    private String status;

    public DefaultServerResponseDto(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
