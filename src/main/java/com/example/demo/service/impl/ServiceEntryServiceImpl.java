package com.example.demo.service.impl;

import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.model.Garage;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.ServiceEntryService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {
    private final ServiceEntryRepository serviceEntryRepository;
    private final VehicleRepository vehicleRepository;
    private final GarageRepository garageRepository;

    public ServiceEntryServiceImpl(ServiceEntryRepository serviceEntryRepository, 
                                   VehicleRepository vehicleRepository, 
                                   GarageRepository garageRepository) {
        this.serviceEntryRepository = serviceEntryRepository;
        this.vehicleRepository = vehicleRepository;
        this.garageRepository = garageRepository;
    }

    @Override
    public ServiceEntry createServiceEntry(ServiceEntry entry) {
        Vehicle vehicle = vehicleRepository.findById(entry.getVehicle().getId()).orElseThrow();
        Garage garage = garageRepository.findById(entry.getGarage().getId()).orElseThrow();

        // Rule 1: Active Vehicles Only (Test 14)
        if (!vehicle.getActive()) {
            throw new IllegalArgumentException("Only active vehicles can receive services");
        }

        // Rule 2: Future Dates Not Allowed (Test 26)
        if (entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Service date cannot be in the future");
        }

        // Rule 3: Odometer Reading Logic (Test 25)
        serviceEntryRepository.findTopByVehicleOrderByOdometerReadingDesc(vehicle).ifPresent(last -> {
            if (entry.getOdometerReading() < last.getOdometerReading()) {
                throw new IllegalArgumentException("Odometer reading must be >= previous readings");
            }
        });

        return serviceEntryRepository.save(entry);
    }

    @Override
    public ServiceEntry getServiceEntryById(Long id) {
        return serviceEntryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ServiceEntry> getEntriesForVehicle(Long vehicleId) {
        return serviceEntryRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<ServiceEntry> getEntriesByGarage(Long garageId) {
        return serviceEntryRepository.findByGarageId(garageId);
    }
}