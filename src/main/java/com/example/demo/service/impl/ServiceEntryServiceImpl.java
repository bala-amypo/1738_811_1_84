package com.example.demo.service.impl;

import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.model.Garage;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.service.ServiceEntryService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    private final ServiceEntryRepository serviceEntryRepository;


    public ServiceEntryServiceImpl(ServiceEntryRepository serviceEntryRepository) {
        this.serviceEntryRepository = serviceEntryRepository;
    }


    @Override
    public ServiceEntry createServiceEntry(ServiceEntry serviceEntry) {

        Vehicle vehicle = serviceEntry.getVehicle();
        Garage garage = serviceEntry.getGarage();

        if (!vehicle.getActive()) {
            throw new RuntimeException("inactive vehicle");
        }

        if (!garage.getActive()) {
            throw new RuntimeException("inactive garage");
        }

        if (serviceEntry.getServiceDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("future");
        }

        List<ServiceEntry> previous = serviceEntryRepository.findByVehicle(vehicle);

        for (ServiceEntry entry : previous) {
            if (serviceEntry.getOdometerReading() < entry.getOdometerReading()) {
                throw new RuntimeException(">=");
            }
        }

        return serviceEntryRepository.save(serviceEntry);
    }
}
