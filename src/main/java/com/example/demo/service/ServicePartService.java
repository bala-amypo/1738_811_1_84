package com.example.demo.service;

import com.example.demo.model.ServicePart;

import java.util.List;

public interface ServicePartService {

    ServicePart addServicePart(ServicePart servicePart);

    List<ServicePart> getPartsByServiceEntry(Long serviceEntryId);
}
