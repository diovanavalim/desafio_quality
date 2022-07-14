package com.dh.mercadolivre.desafioquality.util;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;

import java.util.ArrayList;
import java.util.List;

public class PropertyGenerators {

    public static List<Property> getPropertyList() {
        Room room1 = new Room("Bedroom", 2.00, 1.50);
        Room room2 = new Room("Bathroom", 1.00, 0.50);
        Room room3 = new Room("Kitchen", 1.00, 1.50);
        Room room4 = new Room("Corridor", 3.00, 0.50);

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);

        District district = District.builder()
                .name("Trindade")
                .city("Florianopolis")
                .valueDistrictM2(900.00)
                .build();

        Property property01 = Property.builder()
                .propName("Apartamento Itambe")
                .propDistrict(district)
                .roomList(roomList)
                .id(1)
                .build();

        Property property02 = Property.builder()
                .propName("Apartamento Verdes Mares")
                .propDistrict(district)
                .roomList(roomList)
                .id(2)
                .build();

        Property property03 = Property.builder()
                .propName("Apartamento Manhattan")
                .propDistrict(district)
                .roomList(roomList)
                .id(3)
                .build();

        List<Property> propertyList = new ArrayList<Property>();
        propertyList.add(property01);
        propertyList.add(property02);
        propertyList.add(property03);

        return propertyList;
    }
}
