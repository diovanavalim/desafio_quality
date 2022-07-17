package com.dh.mercadolivre.desafioquality.controller;

import com.dh.mercadolivre.desafioquality.dto.DefaultServerResponseDto;
import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.dto.RoomAreaDto;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Class responsible for intermediating the requests sent by the user with the responses provided by the Service;
 * @author Amanda Marinelli, Diovana Valim, Gabriela Azevedo, Thiago Guimarães, Thiago Frozzi, Rafael Cavalcante
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * A post method responsible for save a new property at the DAO.
     * @param property a valid Property by the request body
     * @return Response Entity of type propertyDto and the corresponding HttpStatus;
     */
    @PostMapping("/property")
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody @Valid Property property) {
        PropertyDto propertyDto = propertyService.saveProperty(property);

        return new ResponseEntity<PropertyDto>(propertyDto, HttpStatus.CREATED);
    }

    /**
     * A get method that when called will return in the body request a property with specified id, if it exists at the DAO
     * @param id a String Property by the URL request
     * @return Response Entity of type propertyDto and the corresponding HttpStatus ;
     */
    @GetMapping("/property/{id}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable String id) {
        long convertedId = Long.parseLong(id);

        PropertyDto propertyDto = propertyService.getProperty(convertedId);

        return new ResponseEntity<PropertyDto>(propertyDto, HttpStatus.OK);
    }

    /**
     * A get method that when called will delete a property with specified id, if it exists at the DAO
     * @param id a String Property by the URL request
     * @return Response Entity of type DefaultServerResponseDto and the corresponding HttpStatus
     */
    @DeleteMapping("/property/{id}")
    public ResponseEntity<DefaultServerResponseDto> deleteProperty(@PathVariable String id) {
        long convertedId = Long.parseLong(id);

        DefaultServerResponseDto defaultServerResponseDto = propertyService.deleteProperty(convertedId);

        return new ResponseEntity<>(defaultServerResponseDto, HttpStatus.OK);
    }

    /**
     * A get method that when called will get the property`s total area with specified id, if it exists at the DAO
     * @param id a String Property by the URL request
     * @return  Response Entity of type DefaultServerResponseDto and the corresponding HttpStatus
     */
    @GetMapping("/property/{id}/total_property_area")
    public ResponseEntity<Double> getTotalPropertyArea(@PathVariable Long id) {
        Double totalPropertyArea = propertyService.getAreaTotal(id);

        return ResponseEntity.ok(totalPropertyArea);
    }

    /**
     * A get method that when called will get the property`s total area with specified id, if it exists at the DAO
     * @param id a Long Property by the URL request
     * @return  Response Entity of type property`s total price and the corresponding HttpStatus
     */
    @GetMapping("/property/{id}/total_property_price")
    public ResponseEntity<Double> getTotalPropertyPrice(@PathVariable Long id) {
        Double totalPropertyPrice = propertyService.calculateTotalPropertyPrice(id);

        return ResponseEntity.ok(totalPropertyPrice);
    }


    /**
     * A get method that when called will get the property`s largest room area with specified id, if it exists at the DAO.
     * @param id a Long Property by the URL request
     * @return  Response Entity of type roomAreaDto (name and area) and the corresponding HttpStatus
     */
    @GetMapping("/property/{id}/largest_room")
    public ResponseEntity<RoomAreaDto> getLargestRoom(@PathVariable Long id) {
        RoomAreaDto largestRoom = propertyService.getLargestRoomFromId(id);

        return ResponseEntity.ok(largestRoom);
    }

    /**
     * A get method that when called will get the property`s largest room area with specified id, if it exists at the DAO.
     * @param id a Long Property by the URL request
     * @return  Response Entity of type list of roomAreaDto (name and area) and the corresponding HttpStatus
     */
    @GetMapping("/property/{id}/list_rooms")
    public ResponseEntity<List<RoomAreaDto>> getListRoom(@PathVariable Long id) {
        List<RoomAreaDto> listRoom = propertyService.getAreaRooms(id);

        return ResponseEntity.ok(listRoom);
    }
}
