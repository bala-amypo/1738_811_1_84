package com.example.demo.controller;

import com.example.demo.model.ServiceEntry;
import com.example.demo.service.ServiceEntryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/service-entries")
public class ServiceEntryController {

    private final ServiceEntryService serviceEntryService;

    public ServiceEntryController(ServiceEntryService serviceEntryService) {
        this.serviceEntryService = serviceEntryService;
    }

    @PostMapping
    public ServiceEntry createServiceEntry(@RequestBody ServiceEntry serviceEntry) {
        return serviceEntryService.createServiceEntry(serviceEntry);
    }

    @GetMapping("/{id}")
    public ServiceEntry getServiceEntryById(@PathVariable Long id) {
        return serviceEntryService.getServiceEntryById(id);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<ServiceEntry> getEntriesByVehicle(@PathVariable Long vehicleId) {
        return serviceEntryService.getServiceEntriesByVehicle(vehicleId);
    }

    @GetMapping("/garage/{garageId}/odometer/{minOdometer}")
    public List<ServiceEntry> getEntriesByGarageAndOdometer(
            @PathVariable Long garageId,
            @PathVariable Integer minOdometer) {
        return serviceEntryService.getServiceEntriesByGarageAndOdometer(garageId, minOdometer);
    }

    @GetMapping("/vehicle/{vehicleId}/date")
    public List<ServiceEntry> getEntriesByVehicleAndDate(
            @PathVariable Long vehicleId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return serviceEntryService.getServiceEntriesByVehicleAndDate(vehicleId, from, to);
    }
}
