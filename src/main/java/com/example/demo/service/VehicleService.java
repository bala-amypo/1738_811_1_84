package com.example.demo.service;

import com.example.demo.model.Vehicle;

public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicle);

    Vehicle getVehicle(Long id);
}
