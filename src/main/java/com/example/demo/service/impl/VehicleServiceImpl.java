package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DuplicateVinException;
import com.example.demo.exception.VehicleNotFoundException;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository repo;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        if (repo.existsByVin(vehicle.getVin())) {
            throw new DuplicateVinException("VIN already exists");
        }
        return repo.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found with id " + id));
    }

    @Override
    public Vehicle getVehicleByVin(String vin) {
        return repo.findByVin(vin)
                .orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found with VIN " + vin));
    }

    @Override
    public List<Vehicle> getVehiclesByOwner(Long ownerId) {
        return repo.findByOwnerId(ownerId);
    }

    @Override
    public void deactivateVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);

        if (!vehicle.getActive()) {
            throw new RuntimeException("Vehicle already deactivated");
        }

        vehicle.setActive(false);
        repo.save(vehicle);
    }
}
