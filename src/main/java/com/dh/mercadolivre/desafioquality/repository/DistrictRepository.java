package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistrictRepository {

    private final String filePath = "src/main/resources/district.json";

    @Autowired
    private FileHandler fileHandler;

    @SuppressWarnings("unchecked")
    public void saveDistrict(District district) {
        fileHandler.addObjectToFile(filePath, "com.dh.mercadolivre.desafioquality.model.District", district);
    }

    @SuppressWarnings("unchecked")
    public List<District> getAllDistrict() {
        List<District> districtList = fileHandler
                .readFile(filePath, "com.dh.mercadolivre.desafioquality.model.District");

        return districtList;
    }
}
