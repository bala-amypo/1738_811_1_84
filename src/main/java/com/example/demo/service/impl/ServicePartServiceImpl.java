package com.example.demo.service.impl;

import com.example.demo.model.ServicePart;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.service.ServicePartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePartServiceImpl implements ServicePartService {

    private final ServicePartRepository servicePartRepository;

    public ServicePartServiceImpl(ServicePartRepository servicePartRepository) {
        this.servicePartRepository = servicePartRepository;
    }

    @Override
    public ServicePart addServicePart(ServicePart servicePart) {

        if (servicePart.getQuantity() <= 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }

        return servicePartRepository.save(servicePart);
    }

    @Override
    public List<ServicePart> getPartsByServiceEntry(Long serviceEntryId) {
        return servicePartRepository.findByServiceEntryId(serviceEntryId);
    }
}
