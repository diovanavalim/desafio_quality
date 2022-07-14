package com.dh.mercadolivre.desafioquality.dto;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDto {
    private String propName;
    private District propDistrict;
    private List<Room> roomList;
    private long id;

    public PropertyDto(Property property) {
        this.propName = property.getPropName();
        this.propDistrict = property.getPropDistrict();
        this.roomList = property.getRoomList();
        this.id = property.getId();
    }
}
