package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.model.Property;
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropertyRepositoryTest {

    @InjectMocks
    PropertyRepository propertyRepository;

    @Mock
    FileHandler fileHandler;

    @BeforeEach
    public void setup() {
        BDDMockito.when(fileHandler.readFile(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(PropertyGenerators.getPropertyList());
    }

    @Test
    void getProperty() {
        List<Property> propertyList = PropertyGenerators.getPropertyList();

        Property wantedProperty = propertyList.get(0);

        Property returnedProperty = propertyRepository.getProperty(wantedProperty.getId());

        assertThat(returnedProperty.getPropName()).isEqualTo(wantedProperty.getPropName());
        assertThat(returnedProperty.getPropDistrict().getName()).isEqualTo(wantedProperty.getPropDistrict().getName());
        assertThat(returnedProperty.getPropDistrict().getValueDistrictM2())
                .isEqualTo(wantedProperty.getPropDistrict().getValueDistrictM2());
        assertThat(returnedProperty.getId()).isEqualTo(wantedProperty.getId());
    }
}