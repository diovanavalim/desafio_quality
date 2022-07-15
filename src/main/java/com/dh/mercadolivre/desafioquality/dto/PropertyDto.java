package com.dh.mercadolivre.desafioquality.dto;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;

import lombok.*;

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
 * Method Constructor with all arguments implemented by Lombok lib
 */
@AllArgsConstructor
/**
 * Method builder implemented by Lombok lib
 */
@Builder
/**
 *class for a data tranfers Object for a Property
 * @author Diovana valim
 * @version 0.0.1
 * @see java.lang.Object
 */
public class PropertyDto {
    private String propName;
    private District propDistrict;
    private List<Room> roomList;
    private long id;
    /**
     * method for constructing an PropertyDto from a Property
     * @param property an object of type Property
     */
    public PropertyDto(Property property) {
        this.propName = property.getPropName();
        this.propDistrict = property.getPropDistrict();
        this.roomList = property.getRoomList();
        this.id = property.getId();
    }
}




