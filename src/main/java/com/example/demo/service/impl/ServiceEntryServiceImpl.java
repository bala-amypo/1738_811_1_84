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

    @Override
    public ServiceEntry createServiceEntry(ServiceEntry entry) {

        if (!entry.getVehicle().isActive()) {
            throw new RuntimeException("active vehicles");
        }

        if (entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("future");
        }

        if (entry.getOdometerReading() < entry.getVehicle().getLastOdometer()) {
            throw new RuntimeException(">=");
        }

        return serviceEntryRepository.save(entry);
    }

    @Override
    public ServiceEntry getServiceEntryById(Long id) {
        return serviceEntryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service entry not found"));
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
