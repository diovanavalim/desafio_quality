package com.dh.mercadolivre.desafioquality.util;

import com.dh.mercadolivre.desafioquality.exceptions.ServerException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class FileHandler will be in charge of doing all the data persistence and restore low level operations.
 * Shall be called over the Repository layer files.
 * @author Diovana Valim
 * @version 0.0.1
 */

@Component
public class FileHandler<T> {

    T object;

    /**
     * Method used to insert a new entry into a JSON file.
     * @param filePath string that represents the file relative path.
     * @param classType string that represents the class that the method shall manipulate
     * @param newObject Object instance to be added into the JSON file.
     * @return a boolean that indicates whether the operation has been successfull.
     */
    public boolean addObjectToFile(String filePath, String classType, T newObject) {
        Class<?> clz;

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            clz = Class.forName(classType);
        } catch (ClassNotFoundException e) {
            throw new ServerException(String.format("Invalid class type: %s", classType));
        }

        List<T> primaryList = null;
        List<T> copyList = null;

        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clz);

        try {
            primaryList = mapper.readValue(new File(filePath), type);
            copyList = new ArrayList<T>(primaryList);

            copyList.add(newObject);

            writer.writeValue(new File(filePath), copyList);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            throw new ServerException(String.format("Could not read or write on file: %s", filePath));
        }

        return true;
    }

    /**
     * Method used to read a JSON file.
     * @param filePath string that represents the file relative path.
     * @param classType string that represents the class that the method shall manipulate.
     * @return JSON file content, formatted into a class type instance list.
     */
    public List<T> readFile(String filePath, String classType)  {
        ObjectMapper mapper = new ObjectMapper();
        Class<?> clz;

        try {
            clz = Class.forName(classType);
        } catch (ClassNotFoundException e) {
            throw new ServerException(String.format("Invalid type: %s", classType));
        }

        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clz);

        List<T> primaryList = null;

        try {
            primaryList = mapper.readValue(new File(filePath), type);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            throw new ServerException(String.format("Could not read on file: %s", filePath));
        }

        return primaryList;
    }

    /**
     * Method used to remove an entry of a JSON file.
     * @param filePath string that represents the file relative path.
     * @param classType string that represents the class that the method shall manipulate
     * @param index integer that indicates the target instance position on a JSON file array.
     * @return a boolean that indicates whether the operation has been successfull.
     */
    public boolean removeObjectFromFile(String filePath, String classType, int index) {
        Class<?> clz;

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            clz = Class.forName(classType);
        } catch (ClassNotFoundException e) {
            throw new ServerException(String.format("Invalid class type: %s", classType));
        }

        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clz);

        List<T> primaryList = null;
        List<T> copyList = null;

        try {
            primaryList = mapper.readValue(new File(filePath), type);
            copyList = new ArrayList<T>(primaryList);

            copyList.remove(index);

            writer.writeValue(new File(filePath), copyList);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            throw new ServerException(String.format("Could not read or write on file: %s", filePath));
        }

        return true;
    }
}
