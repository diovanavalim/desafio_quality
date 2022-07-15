package com.dh.mercadolivre.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomAreaDto {
    private String roomName;
    private Double area;
}
