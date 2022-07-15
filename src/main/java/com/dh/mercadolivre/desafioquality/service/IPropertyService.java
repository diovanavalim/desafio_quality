package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.dto.DefaultServerResponseDto;
import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.dto.PropertyDto;

import java.util.List;

/**
 * Interface to deal with the methods implemented on the PropertyService class.
 */
public interface IPropertyService {
    Double getAreaTotal(Long id);
    
    List<RoomAreaDto> getAreaRooms(Long id);

    PropertyDto saveProperty(Property property);

    PropertyDto getProperty(Long id);

    DefaultServerResponseDto deleteProperty(Long id);
}
