package com.dh.mercadolivre.desafioquality.util;

import com.dh.mercadolivre.desafioquality.dto.DefaultServerResponseDto;
import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;

import java.util.ArrayList;
import java.util.List;

public class TestUtilsGenerator {

    public static Property createPropertyWithId() {
        District district = new District("Ipiranga", "São José", 1000.0);
        Room room1 = new Room("Bedroom", 2.00, 1.50);
        Room room2 = new Room("Bathroom", 1.00, 0.50);
        Room room3 = new Room("Kitchen", 1.00, 1.50);
        Room room4 = new Room("Corridor", 3.00, 0.50);

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);

        return Property.builder()
                .id(1L)
                .propName("Marcos")
                .propDistrict(district)
                .roomList(roomList)
                .build();
    }

    public static PropertyDto getPropertyDtoWithId() {

        District district = new District("Ipiranga", "São José", 1000.0);
        Room room1 = new Room("Bedroom", 2.00, 1.50);
        Room room2 = new Room("Bathroom", 1.00, 0.50);
        Room room3 = new Room("Kitchen", 1.00, 1.50);
        Room room4 = new Room("Corridor", 3.00, 0.50);

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);
        PropertyDto propertyDto = new PropertyDto("Marcos", district, roomList, 1L);

        /*
        return PropertyDto.builder()
                .propName("Marcos")
                .propDistrict(district)
                .roomList(roomList)
                .id(1L)
                .build();*/
        return propertyDto;
    }

    public static Double getDoubleReturn() {
        return 1000.00;
    }

    public static RoomAreaDto getRoomAreaDto() {
        RoomAreaDto room = new RoomAreaDto("Bedroom", 15.00);
        return  room;
    }

    public static List<RoomAreaDto> getListRoomDto() {
        RoomAreaDto room = new RoomAreaDto("Bedroom", 15.00);
        List<RoomAreaDto> listRoomAreaDto = new ArrayList<>();
        listRoomAreaDto.add(room);
        return listRoomAreaDto;
    }

    public static DefaultServerResponseDto returnResponseDto() {
        DefaultServerResponseDto responseDto = new DefaultServerResponseDto("Property successfully deleted", "200");
        return responseDto;
    }
}
