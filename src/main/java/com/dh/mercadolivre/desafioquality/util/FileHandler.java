package com.dh.mercadolivre.desafioquality.util;

import com.dh.mercadolivre.desafioquality.exceptions.ServerException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileHandler<T> {

    T object;

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
