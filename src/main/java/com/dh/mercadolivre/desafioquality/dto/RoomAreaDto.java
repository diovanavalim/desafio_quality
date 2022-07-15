package com.dh.mercadolivre.desafioquality.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Method Getter/setter implemented by Lombok lib
 */
@Data
/**
 * Method Default Constructor implemented by Lombok lib
 */
@NoArgsConstructor
/**
 * Method Constructor with all arguments implemented by Lombok lib
 */
@AllArgsConstructor
/**
 * Method builder implemented by Lombok lib
 */
@Builder

/**
 *class for a data tranfers Object for a room with the calculated area
 * @author Rafael Cavalcante
 * @version 0.0.1
 * @see java.lang.Object
 */
public class RoomAreaDto {
    private String roomName;
    private Double area;
}

