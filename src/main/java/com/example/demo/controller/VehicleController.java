package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    VehicleService ser;

    @PostMapping
    public Vehicle registerVehicle(@RequestBody Vehicle vehicle) {
        return ser.createVehicle(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return ser.getVehicleById(id);
    }

    @GetMapping("/vin/{vin}")
    public Vehicle getVehicleByVin(@PathVariable String vin) {
        return ser.getVehicleByVin(vin);
    }

    @GetMapping("/owner/{ownerId}")
    public List<Vehicle> getVehiclesByOwner(@PathVariable Long ownerId) {
        return ser.getVehiclesByOwner(ownerId);
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateVehicle(@PathVariable Long id) {
        ser.deactivateVehicle(id);
        return "Vehicle deactivated successfully";
    }
}
