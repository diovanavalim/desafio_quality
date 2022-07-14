package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.exceptions.NotFoundPropertyException;
import com.dh.mercadolivre.desafioquality.model.Property;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Repository
public class PropertyRepository {

	private String linkFile = "src/main/resources/property.json";

	public Property findPropertyById(Long id) {

//		Room room1 = new Room("Bedroom", 2.00, 1.50);
//		Room room2 = new Room("Bathroom", 1.00, 0.50);
//		Room room3 = new Room("Kitchen", 1.00, 1.50);
//		Room room4 = new Room("Corridor", 3.00, 0.50);
//		District district = new District("Horto", 900.00);
//		List<Room> listRoom = new ArrayList<>();
//		listRoom.add(room1);
//		listRoom.add(room2);
//		listRoom.add(room3);
//		listRoom.add(room4);
//		Property property = new Property(1L,"Rua Laura", district, listRoom);
//
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectWriter writer = mapper.writer (new DefaultPrettyPrinter());
//		List<Property> lista = new ArrayList<>();
//		lista.add(property);
//
//		try{
//			writer.writeValue(new File(linkFile), lista);
//		}catch (Exception e){
//
//		}
//
//		return property;

		ObjectMapper mapper = new ObjectMapper();
		List<Property> lista = null;
		try {
			lista = Arrays.asList
					(mapper.readValue(new File(linkFile),Property[].class));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		for (Property p : lista) {
			if (p.getId().equals(id)) {
				return p;
			}
		}

		throw new NotFoundPropertyException("Veiculo não localizado");
	}
}
