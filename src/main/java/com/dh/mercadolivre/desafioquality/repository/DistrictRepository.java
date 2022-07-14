package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.util.FileHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistrictRepository {

    private final String filePath = "src/main/resources/district.json";

    public District saveDistrict(District district) {
        FileHandler<District> fileHandler = new FileHandler<District>();

        fileHandler.addObjectToFile(filePath, "com.dh.mercadolivre.desafioquality.model.District", district);

        return district;
    }

    public List<District> getAllDistrict() {
        FileHandler<District> fileHandler = new FileHandler<District>();

        List<District> districtList = fileHandler.readFile(filePath, "com.dh.mercadolivre.desafioquality.model.District");

        System.out.println(districtList);

        return districtList;
    }
}
