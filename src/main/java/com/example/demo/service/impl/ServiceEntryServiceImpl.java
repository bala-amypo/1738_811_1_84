package com.example.demo.service.impl;

import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ServiceEntryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    private final ServiceEntryRepository serviceEntryRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceEntryServiceImpl(ServiceEntryRepository serviceEntryRepository,
                                   VehicleRepository vehicleRepository) {
        this.serviceEntryRepository = serviceEntryRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ServiceEntry createServiceEntry(ServiceEntry serviceEntry) {

        Vehicle vehicle = vehicleRepository.findById(serviceEntry.getVehicle().getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        serviceEntryRepository.findTopByVehicleOrderByOdometerReadingDesc(vehicle)
                .ifPresent(last -> {
                    if (serviceEntry.getOdometerReading() <= last.getOdometerReading()) {
                        throw new IllegalArgumentException("Invalid odometer reading");
                    }
                });

        return serviceEntryRepository.save(serviceEntry);
    }

    @Override
    public ServiceEntry getServiceEntryById(Long id) {
        return serviceEntryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service entry not found"));
    }

    @Override
    public List<ServiceEntry> getServiceEntriesByVehicle(Long vehicleId) {
        return serviceEntryRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<ServiceEntry> getServiceEntriesByGarageAndOdometer(Long garageId, Integer minOdometer) {
        return serviceEntryRepository.findByGarageAndMinOdometer(garageId, minOdometer);
    }

    @Override
    public List<ServiceEntry> getServiceEntriesByVehicleAndDate(Long vehicleId, LocalDate from, LocalDate to) {
        return serviceEntryRepository.findByVehicleAndDateRange(vehicleId, from, to);
    }
}
