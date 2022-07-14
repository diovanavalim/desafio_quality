package com.dh.mercadolivre.desafioquality.dto;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PropertyDto {
    private String propName;
    private District propDistrict;
    private List<Room> roomList;

    public PropertyDto(Property property) {
        this.propName = property.getPropName();
        this.propDistrict = property.getPropDistrict();
        this.roomList = property.getRoomList();
    }
}
