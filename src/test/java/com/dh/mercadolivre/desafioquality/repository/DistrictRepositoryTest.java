package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.District;
import com.dh.mercadolivre.desafioquality.util.DistrictGenerators;
import com.dh.mercadolivre.desafioquality.util.FileHandler;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
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
                .thenReturn(DistrictGenerators.getDistrictList());

        BDDMockito.when(fileHandler.addObjectToFile(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.any(District.class))).thenReturn(true);
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
        List<District> districtList = DistrictGenerators.getDistrictList();

        List<District> returnedDistrictList = districtRepository.getAllDistrict();

        assertThat(returnedDistrictList.size()).isEqualTo(districtList.size());

        for (int i = 0; i < districtList.size(); i++) {
            assertThat(districtList.get(i).getName()).isEqualTo(returnedDistrictList.get(i).getName());
            assertThat(districtList.get(i).getCity()).isEqualTo(returnedDistrictList.get(i).getCity());
            assertThat(districtList.get(i).getValueDistrictM2()).isEqualTo(returnedDistrictList.get(i)
                    .getValueDistrictM2());
        }

        verify(fileHandler, times(1)).readFile(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString());
    }
}