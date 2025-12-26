package com.example.demo.service;

import com.example.demo.model.Garage;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public interface GarageService {
    Garage createGarage(Garage garage);
    Garage updateGarage(Long id, Garage garage) throws EntityNotFoundException;
    Garage getGarageById(Long id) throws EntityNotFoundException;
    List<Garage> getAllGarages();
    void deactivateGarage(Long id) throws EntityNotFoundException;
}
