package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.repository.DistrictRepository;
import com.dh.mercadolivre.desafioquality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService implements IPropertyService{

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public PropertyDto saveProperty(Property property) {
        District district = property.getPropDistrict();

        List<District> districtList = districtRepository.getAllDistrict();

        List<District> districtAlreadyExists = districtList
                .stream()
                .filter(existentDistrict -> existentDistrict.getName().equals(district.getName()) &&
                        existentDistrict.getCity().equals(district.getCity()))
                .collect(Collectors.toList());

        if (districtAlreadyExists.size() == 0) {
            districtRepository.saveDistrict(district);
        }

        List<Property> propertyList = propertyRepository.getAllProperty();

        int lastIndex = propertyList.size() - 1;

        long propertyId = propertyList.get(lastIndex).getId() + 1;

        property.setId(propertyId);

        Property insertedProperty = propertyRepository.saveProperty(property);

        return new PropertyDto(insertedProperty);
    }

    @Override
    public PropertyDto getProperty(Long id) {
        Property property = propertyRepository.getProperty(id);

        return new PropertyDto(property);
    }

    @Override
    public boolean deleteProperty(Long id) {
        Property property = propertyRepository.getProperty(id);

        boolean hasBeenDeleted = propertyRepository.deleteProperty(property);

        return hasBeenDeleted;
    }
}
