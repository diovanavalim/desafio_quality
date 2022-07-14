package com.dh.mercadolivre.desafioquality.service;


import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;
import com.dh.mercadolivre.desafioquality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService implements IPropertyService {

    @Autowired
    private PropertyRepository propertyRep;

    // método que calcula a área total por cômodo de uma propriedade
    private static List<RoomAreaDto> calculatePropertyArea(Property property) {
        List<RoomAreaDto> roomAreaList = property.getRoomList().stream()
                .map(room -> RoomAreaDto.builder()
                        .roomName(room.getRoomName())
                        .area(room.getRoomLength() * room.getRoomWidth())
                        .build())
                .collect(Collectors.toList());
        return roomAreaList;
    };

    private Double calculateTotalPropertyPrice(Long idProperty) {
        // precisamos construir um método GET property by ID
        Room room = new Room("Banheiro", 10.00, 10.00);
        District district = new District("Ipiranga", 1000.00);
        List<Room> listRoom = new ArrayList<>();
        listRoom.add(room);
        Property property = new Property(1L, "Casa da Amanda", district, listRoom);

        return property.getPropDistrict().getValueDistrictM2() * getAreaTotal(idProperty);
    }

    @Override
    public Double getAreaTotal(Long idProperty) {
        Property property = propertyRep.findPropertyById(idProperty);
        List<RoomAreaDto> roomAreaList = calculatePropertyArea(property);
        Double totalArea = 0.0;
        for(RoomAreaDto r : roomAreaList){
            totalArea += r.getArea();
        }
        return totalArea;
    }

	public RoomAreaDto getLargestRoomFromId(Long id){
		Property foundProperty = propertyRep.findPropertyById(id);
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
        Property property = propertyRep.findPropertyById(idProperty);
        List<RoomAreaDto> result = calculatePropertyArea(property);
        return result;
    }
}
