package com.dh.mercadolivre.desafioquality.controller;

import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.service.IPropertyService;
import com.dh.mercadolivre.desafioquality.service.PropertyService;
import com.dh.mercadolivre.desafioquality.util.TestUtilsGenerator;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropertyControllerTest {

    @InjectMocks
    PropertyController controller;

    @Mock
    PropertyService propertyService;

    @BeforeEach
    void setup() {
        BDDMockito.when(propertyService.saveProperty(ArgumentMatchers.any(Property.class)))
                .thenReturn(TestUtilsGenerator.getPropertyDtoWithId());

        BDDMockito.when(propertyService.getProperty(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getPropertyDtoWithId());

        // BDDMockito.doNothing().when(propertyService).deleteProperty(ArgumentMatchers.anyLong());
    }

    @Test
    void saveProperty() {
        Property newProperty = TestUtilsGenerator.createPropertyWithId();
        System.out.println(newProperty.getPropName());

        ResponseEntity<PropertyDto> response = controller.saveProperty(newProperty);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId())
                .isNotNull()
                .isPositive();
        verify(propertyService, atLeastOnce()).saveProperty(newProperty);
    }

    @Test
    void getProperty() {
        PropertyDto propertyDto = TestUtilsGenerator.getPropertyDtoWithId();
        String id = String.valueOf(propertyDto.getId());
        ResponseEntity<PropertyDto> response = controller.getProperty(id);

        verify(propertyService, atLeastOnce()).getProperty(propertyDto.getId());

        assertThat(response.getBody().getId()).isEqualTo(propertyDto.getId());
    }

    @Test
    void getTotalPropertyArea() {
    }

    @Test
    void getTotalPropertyPrice() {

    }

    @Test
    void getLargestRoom() {
    }

    @Test
    void getListRoom() {
    }

    @Test
    void deleteProperty() {
    }
}