package com.dh.mercadolivre.desafioquality.utils;

import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;

import java.util.ArrayList;
import java.util.List;

public class TestUtilsGenerator {
    public static Property generateNewProperty() {
      Room room1 = new Room("Bedroom", 2.00, 1.50);
      Room room2 = new Room("Bathroom", 1.00, 0.50);
      Room room3 = new Room("Kitchen", 1.00, 1.50);
      Room room4 = new Room("Corridor", 3.00, 0.50);
      District district = new District("Horto","City", 900.00);
      List<Room> listRoom = new ArrayList<>();
      listRoom.add(room4);
      listRoom.add(room2);
      listRoom.add(room3);
      listRoom.add(room1);
      Property property = new Property("Casa da Joana", district, listRoom, 1L);

 return property;
    }

    public static Property generateNewPropertyWithoutId() {
        Room room1 = new Room("Bedroom", 2.00, 1.50);
        Room room2 = new Room("Bathroom", 1.00, 0.50);
        Room room3 = new Room("Kitchen", 1.00, 1.50);
        Room room4 = new Room("Corridor", 3.00, 0.50);
        District district = new District("Horto","City", 900.00);
        List<Room> listRoom = new ArrayList<>();
        listRoom.add(room4);
        listRoom.add(room2);
        listRoom.add(room3);
        listRoom.add(room1);
        Property property = Property.builder()
                .propName("Casa da Joana")
                .propDistrict(district)
                .roomList(listRoom)
                .build();

        return property;
    }

    public static Property generateNewPropertyWithoutIdAndNewDistrict() {
        Room room1 = new Room("Bedroom", 2.00, 1.50);
        Room room2 = new Room("Bathroom", 1.00, 0.50);
        Room room3 = new Room("Kitchen", 1.00, 1.50);
        Room room4 = new Room("Corridor", 3.00, 0.50);
        District district = new District("Trybe","Belo Horizonte", 900.00);
        List<Room> listRoom = new ArrayList<>();
        listRoom.add(room4);
        listRoom.add(room2);
        listRoom.add(room3);
        listRoom.add(room1);
        Property property = Property.builder()
                .propName("Casa da Joana")
                .propDistrict(district)
                .roomList(listRoom)
                .build();

        return property;
    }

    public static RoomAreaDto generateLargestRoom() {
        return RoomAreaDto.builder()
                .roomName("Bedroom")
                .area(3.0)
                .build();
    }

    public static List<RoomAreaDto> generateListRoom() {
        List<RoomAreaDto> listRoom = new ArrayList<>();
        listRoom.add(new RoomAreaDto("Corridor", 1.5));
        listRoom.add(new RoomAreaDto("Bathroom", 0.5));
        listRoom.add(new RoomAreaDto("Kitchen", 1.5));
        listRoom.add(new RoomAreaDto("Bedroom", 3.0));

        return listRoom;
    }

    public static List<Property> generateListProperty() {
        List<Property> listProperty = new ArrayList<>();
        listProperty.add(generateNewProperty());

        return listProperty;
    }

    public static List<District> generateNewDistrictList(){
        District district = new District("Horto","City", 900.00);
        List<District> districtList = new ArrayList<>();
        districtList.add(district);
        return districtList;
    }

    public static District generateNewDistrict(){
        District district = new District("Horto","City", 900.00);

        return district;
    }

}
