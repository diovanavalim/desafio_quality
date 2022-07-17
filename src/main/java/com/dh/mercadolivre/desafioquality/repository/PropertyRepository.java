package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dh.mercadolivre.desafioquality.exceptions.PropertyNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import com.dh.mercadolivre.desafioquality.util.FileHandler;

/**
 * Class PropertyRepository will manage data persistence for Property object instances.
 * Has a final attribute that indicates where Property data shall be stored.
 * Will read and save data through the GET and POST requests.
 * @author Diovana Valim, Gabriela Azevedo
 * @version 0.0.1
 */
@Repository
@SuppressWarnings("unchecked")
public class PropertyRepository {

    @Autowired
    private FileHandler fileHandler;

	private final String filePath = "src/main/resources/property.json";

    /**
     * Method used to save a new Property into property.json file.
     * @param property an instance of Property class to be stored in property.json file.
     * @return property instance added to the property.json file.
     * @see com.dh.mercadolivre.desafioquality.util.FileHandler
     */
    public Property saveProperty(Property property) {
        fileHandler.addObjectToFile(filePath, "com.dh.mercadolivre.desafioquality.model.Property", property);

        return property;
    }

    /**
     * Method used to restore all Property instances stored in property.json file.
     * @return a list of Property instances.
     * @see com.dh.mercadolivre.desafioquality.util.FileHandler
     */
    public List<Property> getAllProperty() {
        List<Property> propertyList = fileHandler.readFile(filePath,
                "com.dh.mercadolivre.desafioquality.model.Property");;

        return propertyList;
    }

    /**
     * Method used to restore a single Property instance stored in property.json file.
     * @param id id attribute of the wanted property.
     * @return a Property instance for the given id.
     * @see com.dh.mercadolivre.desafioquality.util.FileHandler
     */
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

    /**
     * Method used to remove a single Property instance stored in property.json file.
     * @param indexOfProperty integer that indicates Property instance position on property.json file array.
     * @return a boolean that indicates whether the operation has been successfull.
     * @see com.dh.mercadolivre.desafioquality.util.FileHandler
     */
    public boolean deleteProperty(int indexOfProperty) {
        return fileHandler.removeObjectFromFile(filePath,
                "com.dh.mercadolivre.desafioquality.model.Property", indexOfProperty);
    }
}
