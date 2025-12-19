package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ServicePart;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.service.ServicePartService;

@Service
public class ServicePartServiceImpl implements ServicePartService {

    @Autowired
    ServicePartRepository repo;

    @Override
    public ServicePart createPart(ServicePart part) {
        return repo.save(part);
    }

    @Override
    public List<ServicePart> getPartsForEntry(Long entryId) {
        return repo.findByServiceEntryId(entryId);
    }

    @Override
    public ServicePart getPartById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
