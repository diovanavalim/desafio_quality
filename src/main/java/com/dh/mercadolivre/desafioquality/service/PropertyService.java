package com.dh.mercadolivre.desafioquality.service;


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
    private List<Double> calculatePropertyArea(Property property) {

        List<Double> calculatedArea = property.getRoomList().stream()
                .map(room -> room.getRoomWidth() * room.getRoomLength())
                .collect(Collectors.toList());
        return calculatedArea;
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
        return calculatePropertyArea(property).stream().reduce(0.0, Double::sum);
    }

	private Room findLargestRoomAmonglist(List<Room> roomList) {
		int largestRoomIndex = 0;
		for (int i = 0; i < roomList.size(); i += 1) {
			if (roomList.get(i).getRoomLength() * roomList.get(i).getRoomWidth() > roomList.get(largestRoomIndex).getRoomLength() * roomList.get(largestRoomIndex).getRoomWidth()) {
				largestRoomIndex = i;
			}
		}
		return roomList.get(largestRoomIndex);
	}

	public Room getLargestRoomFromId(Long id){
		Property FoundProperty = repository.findPropertyById(id);
		return findLargestRoomAmonglist(FoundProperty.getRoomList());
	}
}
