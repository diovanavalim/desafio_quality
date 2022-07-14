package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.dto.PropertyDto;

import java.util.List;

public interface IPropertyService {
    Double getAreaTotal(Long id);
    
    List<RoomAreaDto> getAreaRooms(Long id);

    PropertyDto saveProperty(Property property);

    PropertyDto getProperty(Long id);

    boolean deleteProperty(Long id);
}
