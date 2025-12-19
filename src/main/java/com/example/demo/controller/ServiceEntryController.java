package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ServiceEntry;
import com.example.demo.service.ServiceEntryService;

@RestController
@RequestMapping("/api/service-entries")
public class ServiceEntryController {

    @Autowired
    ServiceEntryService ser;

    @PostMapping
    public ServiceEntry createServiceEntry(@RequestBody ServiceEntry entry) {
        return ser.createServiceEntry(entry);
    }

    @GetMapping("/{id}")
    public ServiceEntry getServiceEntryById(@PathVariable Long id) {
        return ser.getServiceEntryById(id);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<ServiceEntry> getEntriesForVehicle(@PathVariable Long vehicleId) {
        return ser.getEntriesForVehicle(vehicleId);
    }

    @GetMapping("/garage/{garageId}")
    public List<ServiceEntry> getEntriesByGarage(@PathVariable Long garageId) {
        return ser.getEntriesByGarage(garageId);
    }
}
