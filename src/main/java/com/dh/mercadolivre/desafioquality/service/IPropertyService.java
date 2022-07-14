package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.Property;

import java.util.List;

public interface IPropertyService {
    Double getAreaTotal(Long id);
    List<RoomAreaDto> getAreaRooms(Long id);
}
