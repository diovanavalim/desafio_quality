package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;
import com.dh.mercadolivre.desafioquality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

	@Autowired
	PropertyRepository repository;

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
