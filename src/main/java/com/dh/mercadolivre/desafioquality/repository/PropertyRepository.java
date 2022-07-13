package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Repository
public class PropertyRepository {

	Room room = new Room("Banheiro", 10.00, 10.00);
	District district = new District("Ipiranga", 1000.00);
	List<Room> listRoom = new ArrayList<>();
        listRoom.add(room);
	Property property = new Property("Casa da Amanda", district, listRoom);

	private String linkFile = "src/main/resources/property.json";

	public Property findPropertyById(Long id) {
		ObjectMapper mapper = new ObjectMapper();
		List<Property> lista = null;
		try {
			lista = Arrays.asList
					(mapper.readValue(new File(linkFile), Property[].class));
		} catch (Exception ex) {

		}
		for (Property p : lista) {
			if (p.getId().equals(id)) {
				return p;
			}
		}

//		throw new NotFoundException("Veiculo não localizado");
        return null;
	}

}
