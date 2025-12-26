package com.example.demo.service.impl;

import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.GarageService;
import java.util.List;

public class GarageServiceImpl implements GarageService {
    private final GarageRepository garageRepository;

    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    public Garage createGarage(Garage garage) {
        if (garageRepository.findByGarageName(garage.getGarageName()).isPresent()) {
            throw new IllegalArgumentException("Garage name already exists");
        }
        return garageRepository.save(garage);
    }

    public Garage updateGarage(Long id, Garage garage) {
        Garage existing = getGarageById(id);
        existing.setAddress(garage.getAddress());
        existing.setContactNumber(garage.getContactNumber());
        return garageRepository.save(existing);
    }

    public Garage getGarageById(Long id) {
        return garageRepository.findById(id).orElseThrow();
    }

    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    public void deactivateGarage(Long id) {
        Garage g = getGarageById(id);
        g.setActive(false);
        garageRepository.save(g);
    }
}