package com.example.demo.service;

import com.example.demo.model.ServiceEntry;
import java.util.List;

public interface ServiceEntryService {

    List<ServiceEntry> getEntriesForVehicle(Long vehicleId);
}
