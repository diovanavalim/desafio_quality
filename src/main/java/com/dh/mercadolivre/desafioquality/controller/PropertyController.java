package com.dh.mercadolivre.desafioquality.controller;

import com.dh.mercadolivre.desafioquality.dto.PropertyDto;
import com.dh.mercadolivre.desafioquality.model.Property;
import com.dh.mercadolivre.desafioquality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/property")
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody @Valid Property property) {
        PropertyDto propertyDto = propertyService.saveProperty(property);

        return new ResponseEntity<PropertyDto>(propertyDto, HttpStatus.OK);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable String id) {
        long convertedId = Long.parseLong(id);

        PropertyDto propertyDto = propertyService.getProperty(convertedId);

        return new ResponseEntity<PropertyDto>(propertyDto, HttpStatus.OK);
    }

    @DeleteMapping("/property/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable String id) {
        long convertedId = Long.parseLong(id);

        boolean hasBeenDeleted = propertyService.deleteProperty(convertedId);

        String message = hasBeenDeleted ? "Property successfully deleted" : "Could not delete property";

        HttpStatus httpStatus = hasBeenDeleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<String>(message, httpStatus);
    }
}
