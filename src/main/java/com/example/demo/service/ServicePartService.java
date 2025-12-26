package com.example.demo.service;

import com.example.demo.model.ServicePart;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public interface ServicePartService {
    ServicePart createPart(ServicePart part);
    ServicePart getPartById(Long id) throws EntityNotFoundException;
    List<ServicePart> getPartsForEntry(Long entryId);
}
