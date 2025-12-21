package com.example.demo.service;

import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.model.Garage;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.GarageRepository;
import com.example.demo.exception.ServiceEntryException;
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
        Vehicle vehicle = vehicleRepository.findById(entry.getVehicle().getId())
                .orElseThrow(() -> new ServiceEntryException("Vehicle not found"));

        if (!vehicle.isActive()) {
            throw new ServiceEntryException("Only active vehicles allowed");
        }

        if (entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new ServiceEntryException("Service date cannot be in the future");
        }

        Integer lastOdometer = serviceEntryRepository.findTopByVehicleOrderByServiceDateDesc(vehicle)
                .map(ServiceEntry::getOdometerReading)
                .orElse(0);

        if (entry.getOdometerReading() < lastOdometer) {
            throw new ServiceEntryException("Odometer reading must be >= previous reading");
        }

        return serviceEntryRepository.save(entry);
    }

    @Override
    public ServiceEntry getServiceEntryById(Long id) {
        return serviceEntryRepository.findById(id)
                .orElseThrow(() -> new ServiceEntryException("Service entry not found"));
    }

    @Override
    public List<ServiceEntry> getEntriesForVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ServiceEntryException("Vehicle not found"));
        return serviceEntryRepository.findByVehicle(vehicle);
    }

    @Override
    public List<ServiceEntry> getEntriesByGarage(Long garageId) {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ServiceEntryException("Garage not found"));
        return serviceEntryRepository.findByGarage(garage);
    }
}
