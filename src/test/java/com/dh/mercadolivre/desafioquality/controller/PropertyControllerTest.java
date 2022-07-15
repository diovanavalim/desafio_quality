package com.dh.mercadolivre.desafioquality.controller;

import com.dh.mercadolivre.desafioquality.dto.DefaultServerResponseDto;
import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.service.PropertyService;
import com.dh.mercadolivre.desafioquality.util.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import java.util.List;

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

        BDDMockito.when(propertyService.getAreaTotal(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getDoubleReturn());

        BDDMockito.when(propertyService.calculateTotalPropertyPrice(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getDoubleReturn());

        BDDMockito.when(propertyService.getLargestRoomFromId(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getRoomAreaDto());

        BDDMockito.when(propertyService.getAreaRooms(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getListRoomDto());

        BDDMockito.when(propertyService.deleteProperty(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.returnResponseDto());

    }

    @Test
    @DisplayName("Tests the method that saves a new property")
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
    @DisplayName("Tests the method that gets a property by ID")
    void getProperty() {
        PropertyDto propertyDto = TestUtilsGenerator.getPropertyDtoWithId();
        String id = String.valueOf(propertyDto.getId());
        ResponseEntity<PropertyDto> response = controller.getProperty(id);

        verify(propertyService, atLeastOnce()).getProperty(propertyDto.getId());

        assertThat(response.getBody().getId()).isEqualTo(propertyDto.getId());
    }

    @Test
    @DisplayName("Tests the method that gets the property's total area")
    void getTotalPropertyArea() {
        Property newProperty = TestUtilsGenerator.createPropertyWithId();

        ResponseEntity<Double> response = controller.getTotalPropertyArea(newProperty.getId());

        verify(propertyService, atLeastOnce()).getAreaTotal(newProperty.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Tests the method that gets the property's total price")
    void getTotalPropertyPrice() {
        Property newProperty = TestUtilsGenerator.createPropertyWithId();

        ResponseEntity<Double> response = controller.getTotalPropertyPrice(newProperty.getId());

        verify(propertyService, atLeastOnce()).calculateTotalPropertyPrice(newProperty.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Tests the method that gets the property's total area")
    void getLargestRoom() {
        Property newProperty = TestUtilsGenerator.createPropertyWithId();

        ResponseEntity<RoomAreaDto> response = controller.getLargestRoom(newProperty.getId());

        verify(propertyService, atLeastOnce()).getLargestRoomFromId(newProperty.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Tests the method that gets a property's rooms list")
    void getListRoom() {
        Property newProperty = TestUtilsGenerator.createPropertyWithId();

        ResponseEntity <List<RoomAreaDto>> response = controller.getListRoom(newProperty.getId());

        verify(propertyService, atLeastOnce()).getAreaRooms(newProperty.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Tests the method that return success of property delete")
    void successDeleteProperty() {
        Property newProperty = TestUtilsGenerator.createPropertyWithId();
        String convertedId = String.valueOf(newProperty.getId());

        ResponseEntity <DefaultServerResponseDto> response = controller.deleteProperty(convertedId);

        verify(propertyService, atLeastOnce()).deleteProperty(newProperty.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMessage()).isEqualTo("Property successfully deleted");
        int statusCode = Integer.parseInt(response.getBody().getStatus());
        assertThat(statusCode).isEqualTo(200);
    }
}