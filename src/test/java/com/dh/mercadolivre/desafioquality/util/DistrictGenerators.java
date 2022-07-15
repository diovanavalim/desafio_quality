package com.dh.mercadolivre.desafioquality.util;

import com.dh.mercadolivre.desafioquality.model.District;

public class DistrictGenerators {

    public static District getDistrict() {
        return District.builder()
                .name("Carvoeira")
                .city("Florianopolis")
                .valueDistrictM2(600.00)
                .build();
    }
}
