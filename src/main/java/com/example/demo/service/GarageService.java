package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Garage;

public interface GarageService {

    Garage createGarage(Garage garage);

    Garage updateGarage(Long id, Garage garage);

    Garage getGarageById(Long id);

    List<Garage> getAllGarages();

    void deactivateGarage(Long id);
}
