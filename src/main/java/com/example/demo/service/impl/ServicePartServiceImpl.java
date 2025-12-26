package com.example.demo.service.impl;

import com.example.demo.model.ServicePart;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.service.ServicePartService;
import java.util.List;

public class ServicePartServiceImpl implements ServicePartService {
    private final ServicePartRepository servicePartRepository;

    public ServicePartServiceImpl(ServicePartRepository servicePartRepository) {
        this.servicePartRepository = servicePartRepository;
    }

    public ServicePart createPart(ServicePart part) {
        if (part.getQuantity() == null || part.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        return servicePartRepository.save(part);
    }

    public List<ServicePart> getPartsForEntry(Long entryId) {
        return servicePartRepository.findByServiceEntryId(entryId);
    }

    public ServicePart getPartById(Long id) {
        return servicePartRepository.findById(id).orElseThrow();
    }
}