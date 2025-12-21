package com.example.demo.controller;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService ser;

    @PostMapping
    public Vehicle registerVehicle(@Valid @RequestBody Vehicle vehicle) {
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
