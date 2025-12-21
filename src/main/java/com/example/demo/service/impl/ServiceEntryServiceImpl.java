package com.example.demo.service.impl;

import com.example.demo.model.ServiceEntry;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.service.ServiceEntryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    @Autowired
    private ServiceEntryRepository serviceEntryRepository;

    @Override
    public ServiceEntry createServiceEntry(ServiceEntry entry) {

        if (!entry.getVehicle().getActive()) {
            throw new RuntimeException("active vehicles");
        }

        if (entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("future");
        }

        ServiceEntry lastEntry =
                serviceEntryRepository.findTopByVehicleIdOrderByServiceDateDesc(
                        entry.getVehicle().getId());

        if (lastEntry != null &&
            entry.getOdometerReading() < lastEntry.getOdometerReading()) {
            throw new RuntimeException(">=");
        }

        return serviceEntryRepository.save(entry);
    }
}

