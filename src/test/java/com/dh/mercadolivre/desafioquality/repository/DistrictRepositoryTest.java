package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.util.DistrictGenerators;
import com.dh.mercadolivre.desafioquality.util.FileHandler;
import com.dh.mercadolivre.desafioquality.util.PropertyGenerators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SuppressWarnings("unchecked")
class DistrictRepositoryTest {

    @InjectMocks
    DistrictRepository districtRepository;

    @Mock
    FileHandler fileHandler;

    @BeforeEach
    void setup() {
        BDDMockito.when(fileHandler.readFile(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(PropertyGenerators.getPropertyList());

        BDDMockito.when(fileHandler.addObjectToFile(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.any(District.class))).thenReturn(true);

        BDDMockito.when(fileHandler.removeObjectFromFile(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt())).thenReturn(true);
    }

    @Test
    void saveDistrict() {
        District district = DistrictGenerators.getDistrict();
        districtRepository.saveDistrict(district);

        verify(fileHandler, times(1)).addObjectToFile(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.any(District.class));
    }

    @Test
    void getAllDistrict() {
    }
}