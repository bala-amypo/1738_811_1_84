package com.example.demo.service;

import com.example.demo.model.ServiceEntry;

import java.time.LocalDate;
import java.util.List;

public interface ServiceEntryService {

    ServiceEntry createServiceEntry(ServiceEntry serviceEntry);

    ServiceEntry getServiceEntryById(Long id);

    List<ServiceEntry> getServiceEntriesByVehicle(Long vehicleId);

    List<ServiceEntry> getServiceEntriesByGarageAndOdometer(Long garageId, Integer minOdometer);

    List<ServiceEntry> getServiceEntriesByVehicleAndDate(Long vehicleId, LocalDate from, LocalDate to);
}
