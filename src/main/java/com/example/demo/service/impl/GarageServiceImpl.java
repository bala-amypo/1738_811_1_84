package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import java.util.List;
@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;

    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    public Garage createGarage(Garage garage) {
        if (garageRepository.findByGarageName(garage.getGarageName()).isPresent()) {
            throw new IllegalArgumentException("already exists");
        }
        return garageRepository.save(garage);
    }

    public Garage updateGarage(Long id, Garage garage) {
        Garage existing = getGarageById(id);
        existing.setGarageName(garage.getGarageName());
        return garageRepository.save(existing);
    }

    public Garage getGarageById(Long id) {
        return garageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garage not found"));
    }

    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    public void deactivateGarage(Long id) {
        Garage garage = getGarageById(id);
        garage.setActive(false);
        garageRepository.save(garage);
    }
}
