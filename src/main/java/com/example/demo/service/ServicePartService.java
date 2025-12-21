package com.example.demo.service;

import com.example.demo.model.ServicePart;
import java.util.List;

public interface ServicePartService {

    ServicePart createServicePart(ServicePart part);

    ServicePart getServicePartById(Long id);

    List<ServicePart> getPartsByServiceEntry(Long serviceEntryId);
}
