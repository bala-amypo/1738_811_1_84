package com.example.demo.service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.ServicePart;
import com.example.demo.repository.ServicePartRepository;
import java.util.List;

public class ServicePartServiceImpl implements ServicePartService {

    private final ServicePartRepository partRepository;

    public ServicePartServiceImpl(ServicePartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public ServicePart createPart(ServicePart part) {
        if (part.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity");
        }
        return partRepository.save(part);
    }

    public List<ServicePart> getPartsForEntry(Long entryId) {
        return partRepository.findByServiceEntryId(entryId);
    }

    public ServicePart getPartById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part not found"));
    }
}
