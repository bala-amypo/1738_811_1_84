package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ServicePart;

public interface ServicePartService {

    ServicePart createPart(ServicePart part);

    List<ServicePart> getPartsForEntry(Long entryId);

    ServicePart getPartById(Long id);
}
