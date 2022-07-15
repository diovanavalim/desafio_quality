package com.dh.mercadolivre.desafioquality.dto;


import com.dh.mercadolivre.desafioquality.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Method Getter implemented by Lombok lib for get access the private attributes of the Product Class
 */
@Getter
/**
 * Method Setter implemented by Lombok lib for set the private attributes of the Product Class
 */
@Setter
/**
 * Method Default Constructor implemented by Lombok lib
 */
@NoArgsConstructor

/**
 *class for a data tranfers Object for a Property
 * @author Gabriela Azevedo, Amanda Marinelli
 * @version 0.0.1
 * @see java.lang.Object
 */
public class PropDto {
    private String nameProperty;
    private String nameDistrict;
    private Double totalArea;
    private Double totalPrice;
    private List<Room> roomsAreaList;
    private Room largestRoom;

    /**
     * method for constructing an Product Dto from a Product
     * @param nameProperty a String with the name of the property
     * @param nameDistrict a String with the name of the district
     * @param totalArea an Double with the total area of the property
     * @param totalPrice a Double with the total price of the area of the property
     * @param roomsAreaList a list of the rooms of the property with their area
     * @param largestRoom the largest room of the property
     */
    public PropDto(String nameProperty, String nameDistrict, Double totalArea, Double totalPrice, List<Room> roomsAreaList, Room largestRoom) {
        this.nameProperty = nameProperty;
        this.nameDistrict = nameDistrict;
        this.totalArea = totalArea;
        this.totalPrice = totalPrice;
        this.roomsAreaList = roomsAreaList;
        this.largestRoom = largestRoom;
    }
}
