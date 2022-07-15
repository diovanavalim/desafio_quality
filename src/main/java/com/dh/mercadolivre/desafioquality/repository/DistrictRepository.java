package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class DistrictRepository will manage data persistence for District object instances.
 * Has a final attribute that indicates where District data shall be stored.
 * Will read and save data through the GET and POST requests.
 * @author Diovana Valim
 * @version 0.0.1
 */

@Repository
@SuppressWarnings("unchecked")
public class DistrictRepository {

    private final String filePath = "src/main/resources/district.json";

    @Autowired
    private FileHandler fileHandler;

    /**
     * Method used to save a new District into district.json file.
     * @param district an instance of District class to be stored in district.json file.
     * @see com.dh.mercadolivre.desafioquality.util.FileHandler
     */
    public void saveDistrict(District district) {
        fileHandler.addObjectToFile(filePath, "com.dh.mercadolivre.desafioquality.model.District", district);
    }

    /**
     * Method used to restore all District instances stored in district.json file.
     * @return a list of District instances.
     * @see com.dh.mercadolivre.desafioquality.util.FileHandler
     */
    public List<District> getAllDistrict() {
        List<District> districtList = fileHandler
                .readFile(filePath, "com.dh.mercadolivre.desafioquality.model.District");

        return districtList;
    }
}
