package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ServiceEntry;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.service.ServiceEntryService;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    @Autowired
    ServiceEntryRepository repo;

    @Override
    public ServiceEntry createServiceEntry(ServiceEntry entry) {
        return repo.save(entry);
    }

    @Override
    public ServiceEntry getServiceEntryById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<ServiceEntry> getEntriesForVehicle(Long vehicleId) {
        return repo.findByVehicleId(vehicleId);
    }

    @Override
    public List<ServiceEntry> getEntriesByGarage(Long garageId) {
        return repo.findByGarageId(garageId);
    }
}
