package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.dto.DefaultServerResponseDto;
import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;

import com.dh.mercadolivre.desafioquality.exceptions.DistrictNotFoundException;
import com.dh.mercadolivre.desafioquality.exceptions.PropertyNotFoundException;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.repository.DistrictRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements the methods of the IPropertyService interface.
 * @author Diovana Valim, Gabriela Azevedo, Rafael Cavalcante, Thiago Frozzi, Thiago Guimarães, Amanda Marinelli
 * @version 0.0.1
 */
@Service
public class PropertyService implements IPropertyService {

    /**
     * Dependency Injection of the Property Repository.
     */
    @Autowired
    private PropertyRepository propertyRepository;
    /**
     * Dependency Injection of the District Repository.
     */
    @Autowired
    private DistrictRepository districtRepository;

    /**
     * Static Method that receives an object of type Property, and iterate an List to calculate total area property  .
     * @param property object of type Property.
     * @return an list of type RoomAreaDto.
     */
    private static List<RoomAreaDto> calculatePropertyArea(Property property) {
        List<RoomAreaDto> roomAreaList = property.getRoomList().stream()
                .map(room -> RoomAreaDto.builder()
                        .roomName(room.getRoomName())
                        .area(room.getRoomLength() * room.getRoomWidth())
                        .build())
                .collect(Collectors.toList());
        return roomAreaList;
    }

    /**
     * Method that receives an id of type Long, and calculate total property price.
     * @param idProperty of type Long.
     * @return an total Property price type Double.
     */
    public Double calculateTotalPropertyPrice(Long idProperty) {
        List<District> listDistrict = districtRepository.getAllDistrict();
        Property property = propertyRepository.getProperty(idProperty);
        List<District> result = listDistrict.stream().filter((district)-> district.getName().equals(property.getPropDistrict().getName())).collect(Collectors.toList());

        if(result.size() == 0) {
            throw new DistrictNotFoundException("District Not Found");
        }

        return property.getPropDistrict().getValueDistrictM2() * getAreaTotal(idProperty);
    }

    /**
     * Method that receives an id of type Long, and calculate total area of property.
     * @param idProperty of type Long.
     * @return an total Property area type Double.
     */
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

    /**
     * Method that receives an id of type Long, and return the largest Room.
     * @param id of type Long.
     * @return an object type RoomAreaDto.
     */
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

    /**
     * Method that receives an id of type Long, and return list with calculated area of rooms.
     * @param idProperty of type Long.
     * @return an List type RoomAreaDto.
     */
	public List<RoomAreaDto> getAreaRooms(Long idProperty) {
        Property property = propertyRepository.getProperty(idProperty);
        List<RoomAreaDto> result = calculatePropertyArea(property);
        return result;
    }

    /**
     * Static Method if not exist save District.
     * @param property of type Property.
     * @param districtRepository wired object generate by SpringBoot.
     * @return void.
     */
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

    /**
     * Static Method generate new id based atual list of property.
     * @param propertyRepository wired object generate by SpringBoot
     * @return id of type Long.
     */
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

    /**
     * Method to save a single Property in the file.
     * @param property an object of type Property.
     * @return object of PropertyDto.
     */
    @Override
    public PropertyDto saveProperty(Property property) {
        saveNonExistentDistrict(property, districtRepository);

        Long nextId = generateNewLastId(propertyRepository);
        property.setId(nextId);

        Property insertedProperty = propertyRepository.saveProperty(property);

        return new PropertyDto(insertedProperty);
    }

    /**
     * Method to find a property by id and return a PropertyDto.
     * @param id of type Long.
     * @return an object of type PropertyDto.
     */
    @Override
    public PropertyDto getProperty(Long id) {
        Property property = propertyRepository.getProperty(id);

        return new PropertyDto(property);
    }

    /**
     * Method to delete property by id.
     * @param id of type Long
     * @return object of type DefaultServerResponseDto.
     */
    @Override
    public DefaultServerResponseDto deleteProperty(Long id) {
        List<Property> propertyList = propertyRepository.getAllProperty();

        int indexOfProperty = -1;

        for (int i = 0; i < propertyList.size(); i++) {
            if (propertyList.get(i).getId() == id) {
                indexOfProperty = i;
            }
        }

        if (indexOfProperty == -1) {
            throw new PropertyNotFoundException(String.format("Could not find property for id %d", id));
        }

        boolean hasBeenDeleted = propertyRepository.deleteProperty(indexOfProperty);


        String message = hasBeenDeleted ? "Property successfully deleted" : "Could not delete property";
        HttpStatus httpStatus = hasBeenDeleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new DefaultServerResponseDto(message, httpStatus.getReasonPhrase());
    }
}
