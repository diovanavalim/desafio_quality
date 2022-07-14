package com.dh.mercadolivre.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoomAreaDto {
    private String roomName;
    private Double area;
}
