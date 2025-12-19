package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Vehicle;

public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicle);

    Vehicle getVehicleById(Long id);

    Vehicle getVehicleByVin(String vin);

    List<Vehicle> getVehiclesByOwner(Long ownerId);

    void deactivateVehicle(Long id);
}
    