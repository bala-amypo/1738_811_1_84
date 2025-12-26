package com.example.demo.service.impl;

import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.GarageService;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;


    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }


    @Override
    public Garage createGarage(Garage garage) {

        Optional<Garage> existing = garageRepository.findByGarageName(garage.getGarageName());

        if (existing.isPresent()) {
            throw new RuntimeException("already exists");
        }

        return garageRepository.save(garage);
    }


    @Override
    public Garage getGarage(Long id) {

        return garageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garage not found"));
    }
}
