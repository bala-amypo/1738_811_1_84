package com.example.demo.service.impl;

import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;


    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public Vehicle createVehicle(Vehicle vehicle) {

        Optional<Vehicle> existing = vehicleRepository.findByVin(vehicle.getVin());

        if (existing.isPresent()) {
            throw new RuntimeException("VIN");
        }

        return vehicleRepository.save(vehicle);
    }


    @Override
    public Vehicle getVehicle(Long id) {

        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }
}
