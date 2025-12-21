package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ServiceEntry;
import com.example.demo.entity.Vehicle;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ServiceEntryService;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    @Autowired
    private ServiceEntryRepository serviceEntryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ServiceEntry createServiceEntry(ServiceEntry entry) {

        Vehicle vehicle =
                vehicleRepository
                        .findById(entry.getVehicle().getId())
                        .orElseThrow();

        if (!vehicle.getActive()) {
            throw new RuntimeException("active vehicles");
        }

        if (entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("future");
        }

        ServiceEntry lastEntry =
                serviceEntryRepository
                        .findTopByVehicleIdOrderByOdometerReadingDesc(
                                vehicle.getId()
                        );

        if (lastEntry != null &&
                entry.getOdometerReading() < lastEntry.getOdometerReading()) {
            throw new RuntimeException(">=");
        }

        ServiceEntry newEntry =
                new ServiceEntry(
                        entry.getVehicle(),
                        entry.getGarage(),
                        entry.getServiceType(),
                        entry.getServiceDate(),
                        entry.getOdometerReading(),
                        entry.getDescription(),
                        LocalDateTime.now()
                );

        return serviceEntryRepository.save(newEntry);
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
