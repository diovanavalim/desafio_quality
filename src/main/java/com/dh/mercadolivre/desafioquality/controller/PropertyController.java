package com.dh.mercadolivre.desafioquality.controller;
import com.dh.mercadolivre.desafioquality.model.Room;
import com.dh.mercadolivre.desafioquality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	PropertyService service;

	@GetMapping("/largestroom/{id}")
	public ResponseEntity<Room> getLargestRoom(@PathVariable Long id){
		Room largestRoom = service.getLargestRoomFromId(id);

		return ResponseEntity.ok(largestRoom);
	}

	@GetMapping("/listRooms/{id}")
	public ResponseEntity<List<RoomAreaDto>> getListRoom(@PathVariable Long id){
		List<RoomAreaDto> listRoom = service.getAreaRooms(id);

		return ResponseEntity.ok(listRoom);
	}
}
