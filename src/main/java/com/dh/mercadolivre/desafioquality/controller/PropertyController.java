package com.dh.mercadolivre.desafioquality.controller;
import com.dh.mercadolivre.desafioquality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/area/{id}")
    public ResponseEntity<Double> getTotalPropertyArea(@PathVariable Long id) {
        Double result = propertyService.getAreaTotal(id);
        return ResponseEntity.ok(result);
    }
}
