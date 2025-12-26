package com.example.demo.service.impl;

import com.example.demo.model.ServicePart;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.service.ServicePartService;

import org.springframework.stereotype.Service;

@Service
public class ServicePartServiceImpl implements ServicePartService {

    private final ServicePartRepository servicePartRepository;


    public ServicePartServiceImpl(ServicePartRepository servicePartRepository) {
        this.servicePartRepository = servicePartRepository;
    }


    @Override
    public ServicePart createServicePart(ServicePart servicePart) {

        if (servicePart.getQuantity() <= 0) {
            throw new RuntimeException("Quantity");
        }

        return servicePartRepository.save(servicePart);
    }
}
