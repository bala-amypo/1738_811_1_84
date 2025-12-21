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
    private ServiceEntryService serviceEntryService;

    @PostMapping
    public ServiceEntry createServiceEntry(@RequestBody ServiceEntry entry) {
        return serviceEntryService.createServiceEntry(entry);
    }

    @GetMapping("/{id}")
    public ServiceEntry getEntryById(@PathVariable Long id) {
        return serviceEntryService.getServiceEntryById(id);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<ServiceEntry> getEntriesForVehicle(@PathVariable Long vehicleId) {
        return serviceEntryService.getEntriesForVehicle(vehicleId);
    }

    @GetMapping("/garage/{garageId}")
    public List<ServiceEntry> getEntriesByGarage(@PathVariable Long garageId) {
        return serviceEntryService.getEntriesByGarage(garageId);
    }
}
