package com.dh.mercadolivre.desafioquality.dto;


import com.dh.mercadolivre.desafioquality.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PropDto {
    private String nameProperty;
    private String nameDistrict;
    private Double totalArea;
    private Double totalPrice;

    //Vai ser RoomDTO
    private List<Room> roomsAreaList;
    private Room largestRoom;

    public PropDto(String nameProperty, String nameDistrict, Double totalArea, Double totalPrice, List<Room> roomsAreaList, Room largestRoom) {
        this.nameProperty = nameProperty;
        this.nameDistrict = nameDistrict;
        this.totalArea = totalArea;
        this.totalPrice = totalPrice;
        this.roomsAreaList = roomsAreaList;
        this.largestRoom = largestRoom;
    }
}
