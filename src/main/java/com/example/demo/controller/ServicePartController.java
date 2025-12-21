package com.example.demo.controller;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-parts")
public class ServicePartController {

    @Autowired
    private ServicePartService servicePartService;

    @PostMapping
    public ServicePart createServicePart(@Valid @RequestBody ServicePart part) {
        return servicePartService.createServicePart(part);
    }

    @GetMapping("/{id}")
    public ServicePart getServicePartById(@PathVariable Long id) {
        return servicePartService.getServicePartById(id);
    }

    @GetMapping("/service-entry/{serviceEntryId}")
    public List<ServicePart> getByServiceEntry(@PathVariable Long serviceEntryId) {
        return servicePartService.getPartsByServiceEntry(serviceEntryId);
    }
}
