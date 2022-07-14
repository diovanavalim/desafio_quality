package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.exceptions.NotFoundPropertyException;
import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;

import com.dh.mercadolivre.desafioquality.model.Room;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.dh.mercadolivre.desafioquality.exceptions.PropertyNotFoundException;
import com.dh.mercadolivre.desafioquality.model.Property;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import com.dh.mercadolivre.desafioquality.util.FileHandler;

@Repository
public class PropertyRepository {

	private final String filePath = "src/main/resources/property.json";

    public Property saveProperty(Property property) {
        FileHandler<Property> fileHandler = new FileHandler<Property>();

        fileHandler.addObjectToFile(filePath, "com.dh.mercadolivre.desafioquality.model.Property", property);

        return property;
    }

    public List<Property> getAllProperty() {
        FileHandler<Property> fileHandler = new FileHandler<Property>();

        List<Property> propertyList = fileHandler.readFile(filePath,
                "com.dh.mercadolivre.desafioquality.model.Property");;

        return propertyList;
    }

    public Property getProperty(long id) {
        FileHandler<Property> fileHandler = new FileHandler<Property>();

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
        FileHandler<Property> fileHandler = new FileHandler<Property>();

        boolean hasDeletedProperty = fileHandler.removeObjectFromFile(filePath,
                "com.dh.mercadolivre.desafioquality.model.Property", indexOfProperty);

        if (!hasDeletedProperty) {
            throw new PropertyNotFoundException("Could not find property for id");
        }

        return true;
    }
}
