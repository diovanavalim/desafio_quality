package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;

import com.dh.mercadolivre.desafioquality.exceptions.DistrictNotFoundException;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.repository.DistrictRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService implements IPropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private DistrictRepository districtRepository;

    // método que calcula a área total por cômodo de uma propriedade
    private static List<RoomAreaDto> calculatePropertyArea(Property property) {
        List<RoomAreaDto> roomAreaList = property.getRoomList().stream()
                .map(room -> RoomAreaDto.builder()
                        .roomName(room.getRoomName())
                        .area(room.getRoomLength() * room.getRoomWidth())
                        .build())
                .collect(Collectors.toList());
        return roomAreaList;
    }

    // método que calcula o preço do metro quadrado de acordo com a vizinhança
    public Double calculateTotalPropertyPrice(Long idProperty) {
        List<District> listDistrict = districtRepository.getAllDistrict();
        Property property = propertyRepository.getProperty(idProperty);
        List<District> result = listDistrict.stream().filter((district)-> district.getName().equals(property.getPropDistrict().getName())).collect(Collectors.toList());

        if(result.size() == 0) {
            throw new DistrictNotFoundException("District Not Found");
        }

        return property.getPropDistrict().getValueDistrictM2() * getAreaTotal(idProperty);
    }

    @Override
    public Double getAreaTotal(Long idProperty) {
        Property property = propertyRepository.getProperty(idProperty);
        List<RoomAreaDto> roomAreaList = calculatePropertyArea(property);
        Double totalArea = 0.0;
        for(RoomAreaDto r : roomAreaList){
            totalArea += r.getArea();
        }
        return totalArea;
    }

	public RoomAreaDto getLargestRoomFromId(Long id){
		Property foundProperty = propertyRepository.getProperty(id);
		List<RoomAreaDto> roomAreaList = calculatePropertyArea(foundProperty);
		RoomAreaDto largestRoom = roomAreaList.get(0);
        for(RoomAreaDto r : roomAreaList){
            if(r.getArea() > largestRoom.getArea()){
                largestRoom = r;
            }
        }
		return largestRoom;
	}

	public List<RoomAreaDto> getAreaRooms(Long idProperty) {
        Property property = propertyRepository.getProperty(idProperty);
        List<RoomAreaDto> result = calculatePropertyArea(property);
        return result;
    }

    private static void saveNonExistentDistrict(Property property, DistrictRepository districtRepository){
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
    }

    private static long generateNewLastId (PropertyRepository propertyRepository) {
        List<Property> propertyList = propertyRepository.getAllProperty();

        int lastIndex = 0;
        long propertyId = 1;

        if (propertyList.size() != 0) {
            lastIndex = propertyList.size() - 1;
            propertyId = propertyList.get(lastIndex).getId() + 1;
        }
        return propertyId;
    }
    
    @Override
    public PropertyDto saveProperty(Property property) {
        saveNonExistentDistrict(property, districtRepository);

        Long nextId = generateNewLastId(propertyRepository);
        property.setId(nextId);

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
        List<Property> propertyList = propertyRepository.getAllProperty();

        int indexOfProperty = -1;

        for (int i = 0; i < propertyList.size(); i++) {
            if (propertyList.get(i).getId() == id) {
                indexOfProperty = i;
            }
        }

        if (indexOfProperty == -1) {
            return false;
        }

        boolean hasBeenDeleted = propertyRepository.deleteProperty(indexOfProperty);

        return hasBeenDeleted;
    }
}
