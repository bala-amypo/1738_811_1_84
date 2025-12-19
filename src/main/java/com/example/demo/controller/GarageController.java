package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Garage;
import com.example.demo.service.GarageService;

@RestController
@RequestMapping("/api/garages")
public class GarageController {

    @Autowired
    GarageService ser;

    @PostMapping
    public Garage createGarage(@RequestBody Garage garage) {
        return ser.createGarage(garage);
    }

    @PutMapping("/{id}")
    public Garage updateGarage(@PathVariable Long id, @RequestBody Garage garage) {
        return ser.updateGarage(id, garage);
    }

    @GetMapping("/{id}")
    public Garage getGarageById(@PathVariable Long id) {
        return ser.getGarageById(id);
    }

    @GetMapping
    public List<Garage> getAllGarages() {
        return ser.getAllGarages();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateGarage(@PathVariable Long id) {
        ser.deactivateGarage(id);
        return "Garage deactivated successfully";
    }
}
