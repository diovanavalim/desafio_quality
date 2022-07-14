package com.dh.mercadolivre.desafioquality.util;

import com.dh.mercadolivre.desafioquality.exceptions.ServerException;
import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler<T> {

    T object;

    public boolean writeFile(String filePath, String className, T newObject) {
        Class arrayClass = className.equals("district") ? District[].class : Property[].class;

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        List<T> primaryList = null;
        List<T> copyList = null;

        try {
            primaryList = (List<T>) Arrays.asList(mapper.readValue(new File(filePath), arrayClass));
            copyList = new ArrayList<T>(primaryList);

            copyList.add(newObject);

            writer.writeValue(new File(filePath), copyList);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            throw new ServerException(String.format("Could not read or write on file: %s", filePath));
        }

        return true;
    }

    public List<T> readFile(String filePath, String className) {
        Class arrayClass = className.equals("district") ? District[].class : Property[].class;

        ObjectMapper mapper = new ObjectMapper();

        List<T> primaryList = null;

        try {
            primaryList = (List<T>) Arrays.asList(mapper.readValue(new File(filePath), arrayClass));
        } catch (Exception e) {
            System.out.println(e.getMessage());

            throw new ServerException(String.format("Could not read on file: %s", filePath));
        }

        return primaryList;
    }

    public boolean removeObjectFromFile(String filePath, String className, T object) {
        Class arrayClass = className.equals("district") ? District[].class : Property[].class;

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        List<T> primaryList = null;
        List<T> copyList = null;

        try {
            primaryList = (List<T>) Arrays.asList(mapper.readValue(new File(filePath), arrayClass));
            copyList = new ArrayList<T>(primaryList);

            int indexOfProperty = copyList.indexOf(object);

            if (indexOfProperty == -1) {
                return false;
            }

            copyList.remove(indexOfProperty);

            writer.writeValue(new File(filePath), copyList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
