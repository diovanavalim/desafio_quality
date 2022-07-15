package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dh.mercadolivre.desafioquality.exceptions.PropertyNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import com.dh.mercadolivre.desafioquality.util.FileHandler;

@Repository
@SuppressWarnings("unchecked")
public class PropertyRepository {

    @Autowired
    private FileHandler fileHandler;

	private final String filePath = "src/main/resources/property.json";

    public Property saveProperty(Property property) {
        fileHandler.addObjectToFile(filePath, "com.dh.mercadolivre.desafioquality.model.Property", property);

        return property;
    }

    public List<Property> getAllProperty() {
        List<Property> propertyList = fileHandler.readFile(filePath,
                "com.dh.mercadolivre.desafioquality.model.Property");;

        return propertyList;
    }

    public Property getProperty(long id) {
        List<Property> propertyList = fileHandler.readFile(filePath, "com.dh.mercadolivre.desafioquality.model.Property");

        List<Property> propertyExists = propertyList
                .stream()
                .filter(property -> property.getId() == id)
                .collect(Collectors.toList());

        if (propertyExists.size() == 0) {
            throw new PropertyNotFoundException(String.format("Could not find property for id %d", id));
        }

        return propertyExists.get(0);
    }

    public boolean deleteProperty(int indexOfProperty) {
        return fileHandler.removeObjectFromFile(filePath,
                "com.dh.mercadolivre.desafioquality.model.Property", indexOfProperty);
    }
}
