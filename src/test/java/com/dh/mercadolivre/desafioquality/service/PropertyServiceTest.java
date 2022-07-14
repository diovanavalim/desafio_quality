package com.dh.mercadolivre.desafioquality.service;

import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.repository.PropertyRepository;
import com.dh.mercadolivre.desafioquality.utils.TestUtilsGenerator;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropertyServiceTest {

    @InjectMocks
    PropertyService service;

    @Mock
    PropertyRepository propertyRepository;

    @BeforeEach
    public void setup(){
        BDDMockito.when(propertyRepository.getProperty(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.generateNewProperty());
    }

    @Test
    @DisplayName("Testa calculo do valor total do metro quadrado da propriedade")
    void calculateTotalPropertyPrice() {
        Property newProperty = TestUtilsGenerator.generateNewProperty();

       Double result = service.calculateTotalPropertyPrice(newProperty.getId());

       assertThat(result).isEqualTo(5850.0);
       verify(propertyRepository, atLeastOnce()).getProperty(newProperty.getId());

    }

    @Test
    @DisplayName("Testa calculo da area total da propriedade")
    void getAreaTotal() {
        Property newProperty = TestUtilsGenerator.generateNewProperty();

        Double result = service.getAreaTotal(newProperty.getId());

        assertThat(result).isEqualTo(6.5);
        verify(propertyRepository, atLeastOnce()).getProperty(newProperty.getId());
    }

    @Test
    @DisplayName("Testa o maior comodo da propriedade")
    void getLargestRoomFromId() {
        Property newProperty = TestUtilsGenerator.generateNewProperty();
        RoomAreaDto largestRoom = TestUtilsGenerator.generateLargestRoom();
        RoomAreaDto result = service.getLargestRoomFromId(newProperty.getId());

        assertThat(result).isEqualTo(largestRoom);
        verify(propertyRepository, atLeastOnce()).getProperty(newProperty.getId());
    }

    @Test
    @DisplayName("Testa se retorna uma lista de RoomAreaDto")
    void getAreaRooms() {
        Property newProperty = TestUtilsGenerator.generateNewProperty();
        List<RoomAreaDto> listRoom = TestUtilsGenerator.generateListRoom();

        List<RoomAreaDto> result = service.getAreaRooms(newProperty.getId());

        assertThat(result).isEqualTo(listRoom);
        verify(propertyRepository, atLeastOnce()).getProperty(newProperty.getId());
    }
}