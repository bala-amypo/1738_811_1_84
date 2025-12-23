package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.repository.VehicleRepository;

import java.util.Date;
import java.util.List;
@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    private final ServiceEntryRepository entryRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceEntryServiceImpl(ServiceEntryRepository entryRepository,
                                   VehicleRepository vehicleRepository) {
        this.entryRepository = entryRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ServiceEntry createServiceEntry(ServiceEntry entry) {

        Vehicle vehicle = entry.getVehicle();

        if (!vehicle.getActive()) {
            throw new IllegalArgumentException("active vehicles");
        }

        if (entry.getServiceDate().after(new Date())) {
            throw new IllegalArgumentException("future");
        }

        ServiceEntry last = entryRepository.findTopByVehicleOrderByOdometerReadingDesc(vehicle);

        if (last != null && entry.getOdometerReading() < last.getOdometerReading()) {
            throw new IllegalArgumentException(">=");
        }

        return entryRepository.save(entry);
    }

    public ServiceEntry getServiceEntryById(Long id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service entry not found"));
    }

    public List<ServiceEntry> getEntriesForVehicle(Long vehicleId) {
        return entryRepository.findByVehicleId(vehicleId);
    }

    public List<ServiceEntry> getEntriesByGarage(Long garageId) {
        return entryRepository.findByGarageId(garageId);
    }
}
