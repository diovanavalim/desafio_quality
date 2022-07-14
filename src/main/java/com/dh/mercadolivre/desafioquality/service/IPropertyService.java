package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.model.Property;

public interface IPropertyService {
    PropertyDto saveProperty(Property property);

    PropertyDto getProperty(Long id);

    boolean deleteProperty(Long id);
}
