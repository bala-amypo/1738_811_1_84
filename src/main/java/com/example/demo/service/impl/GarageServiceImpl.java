package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.GarageService;

@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    GarageRepository repo;

    @Override
    public Garage createGarage(Garage garage) {
        return repo.save(garage);
    }

    @Override
    public Garage updateGarage(Long id, Garage garage) {
        Garage existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setGarageName(garage.getGarageName());
            existing.setAddress(garage.getAddress());
            existing.setContactNumber(garage.getContactNumber());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public Garage getGarageById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Garage> getAllGarages() {
        return repo.findAll();
    }

    @Override
    public void deactivateGarage(Long id) {
        Garage garage = repo.findById(id).orElse(null);
        if (garage != null) {
            garage.setActive(false);
            repo.save(garage);
        }
    }
}
