package com.example.demo.service;

import com.example.demo.model.ServiceEntry;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public interface ServiceEntryService {
    ServiceEntry createServiceEntry(ServiceEntry entry);
    ServiceEntry getServiceEntryById(Long id) throws EntityNotFoundException;
    List<ServiceEntry> getEntriesForVehicle(Long vehicleId);
    List<ServiceEntry> getEntriesByGarage(Long garageId);
}
