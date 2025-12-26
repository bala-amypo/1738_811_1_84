package com.example.demo.service.impl;

import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.GarageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;

    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    @Override
    public Garage createGarage(Garage garage) {
        Optional<Garage> existing = garageRepository.findByGarageName(garage.getGarageName());
        if (existing.isPresent()) throw new IllegalArgumentException("already exists");
        if (garage.getActive() == null) garage.setActive(true);
        return garageRepository.save(garage);
    }

    @Override
    public Garage updateGarage(Long id, Garage garage) {
        Garage existing = getGarageById(id);
        existing.setGarageName(garage.getGarageName());
        existing.setAddress(garage.getAddress());
        existing.setContactNumber(garage.getContactNumber());
        existing.setActive(garage.getActive());
        return garageRepository.save(existing);
    }

    @Override
    public Garage getGarageById(Long id) {
        return garageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garage not found"));
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    @Override
    public void deactivateGarage(Long id) {
        Garage garage = getGarageById(id);
        garage.setActive(false);
        garageRepository.save(garage);
    }
}
