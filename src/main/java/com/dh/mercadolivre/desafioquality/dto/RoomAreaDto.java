package com.dh.mercadolivre.desafioquality.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomAreaDto {
    private String roomName;
    private Double area;
}
