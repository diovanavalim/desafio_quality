package com.dh.mercadolivre.desafioquality.util;

import com.dh.mercadolivre.desafioquality.model.District;

import java.util.ArrayList;
import java.util.List;

public class DistrictGenerators {

    public static District getDistrict() {
        return District.builder()
                .name("Carvoeira")
                .city("Florianopolis")
                .valueDistrictM2(600.00)
                .build();
    }

    public static List<District> getDistrictList() {
        List<District> districtList = new ArrayList<District>();

        District district01 = District.builder()
                .name("Carvoeira")
                .city("Florianopolis")
                .valueDistrictM2(600.00)
                .build();

        District district02 = District.builder()
                .name("Trindade")
                .city("Florianopolis")
                .valueDistrictM2(900.00)
                .build();

        District district03 = District.builder()
                .name("Saco Grande")
                .city("Florianopolis")
                .valueDistrictM2(600.00)
                .build();

        District district04 = District.builder()
                .name("Itacorubi")
                .city("Florianopolis")
                .valueDistrictM2(900.00)
                .build();

        districtList.add(district01);
        districtList.add(district02);
        districtList.add(district03);
        districtList.add(district04);

        return districtList;
    }
}
