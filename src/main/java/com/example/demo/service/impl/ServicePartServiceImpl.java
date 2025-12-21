package com.example.demo.service.impl;

import com.example.demo.model.ServicePart;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.service.ServicePartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePartServiceImpl implements ServicePartService {

    @Autowired
    private ServicePartRepository servicePartRepository;

    @Override
    public ServicePart createServicePart(ServicePart part) {

        if (part.getQuantity() <= 0) {
            throw new RuntimeException("Quantity");
        }

        return servicePartRepository.save(part);
    }

    @Override
    public ServicePart getServicePartById(Long id) {
        return servicePartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service part not found"));
    }

    @Override
    public List<ServicePart> getPartsByServiceEntry(Long serviceEntryId) {
        return servicePartRepository.findByServiceEntryId(serviceEntryId);
    }
}
