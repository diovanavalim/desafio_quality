package com.dh.mercadolivre.desafioquality.repository;

import com.dh.mercadolivre.desafioquality.exceptions.PropertyNotFoundException;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.util.FileHandler;
import com.dh.mercadolivre.desafioquality.util.PropertyGenerators;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.Mockito.*;

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

        BDDMockito.when(fileHandler.addObjectToFile(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Property.class))).thenReturn(true);

        BDDMockito.when(fileHandler.removeObjectFromFile(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt())).thenReturn(true);
    }

    @Test
    void getProperty_whenPropertyExists() {
        List<Property> propertyList = PropertyGenerators.getPropertyList();

        Property wantedProperty = propertyList.get(0);

        Property returnedProperty = propertyRepository.getProperty(wantedProperty.getId());

        assertThat(returnedProperty.getPropName()).isEqualTo(wantedProperty.getPropName());
        assertThat(returnedProperty.getPropDistrict().getName()).isEqualTo(wantedProperty.getPropDistrict().getName());
        assertThat(returnedProperty.getPropDistrict().getValueDistrictM2())
                .isEqualTo(wantedProperty.getPropDistrict().getValueDistrictM2());
        assertThat(returnedProperty.getId()).isEqualTo(wantedProperty.getId());

        verify(fileHandler, times(1)).readFile(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString());
    }

    @Test
    void getProperty_whenPropertyDoesNotExists() {
        List<Property> propertyList = PropertyGenerators.getPropertyList();

        int lastPropertyId = propertyList.size();
        int inexistentId = lastPropertyId + 1;

        PropertyNotFoundException exception = Assertions.assertThrows(PropertyNotFoundException.class, () -> {
            Property property = propertyRepository.getProperty(inexistentId);
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("Could not find property for id %d", inexistentId));
        verify(fileHandler, times(1)).readFile(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString());
    }

    @Test
    void saveProperty() {
        Property property = PropertyGenerators.getProperty();

        Property returnedProperty = propertyRepository.saveProperty(property);

        assertThat(returnedProperty.getPropName()).isEqualTo(property.getPropName());
        assertThat(returnedProperty.getPropDistrict().getName()).isEqualTo(property.getPropDistrict().getName());
        assertThat(returnedProperty.getPropDistrict().getCity()).isEqualTo(property.getPropDistrict().getCity());
        assertThat(returnedProperty.getId()).isEqualTo(property.getId());

        verify(fileHandler, times(1)).addObjectToFile(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.any(Property.class));
    }

    @Test
    void getAllProperty() {
        List<Property> propertyList = PropertyGenerators.getPropertyList();

        List<Property> returnedPropertyList = propertyRepository.getAllProperty();

        assertThat(returnedPropertyList.size()).isEqualTo(propertyList.size());

        for (int i = 0; i < propertyList.size(); i++) {
            assertThat(returnedPropertyList.get(i).getPropName())
                    .isEqualTo(propertyList.get(i).getPropName());

            assertThat(returnedPropertyList.get(i).getPropDistrict().getName())
                    .isEqualTo(propertyList.get(i).getPropDistrict().getName());

            assertThat(returnedPropertyList.get(i).getPropDistrict().getValueDistrictM2())
                    .isEqualTo(propertyList.get(i).getPropDistrict().getValueDistrictM2());

            assertThat(returnedPropertyList.get(i).getId())
                    .isEqualTo(propertyList.get(i).getId());
        }

        verify(fileHandler, times(1)).readFile(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString());
    }

    @Test
    void deleteProperty_whenPropertyExists() {
        int indexOfProperty = 0;

        boolean hasRemovedProperty = propertyRepository.deleteProperty(indexOfProperty);

        assertThat(hasRemovedProperty).isEqualTo(true);
        verify(fileHandler, times(1)).removeObjectFromFile(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyInt());
    }
}