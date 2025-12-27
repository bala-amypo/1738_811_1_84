package com.example.demo.controller;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-parts")
public class ServicePartController {

    private final ServicePartService servicePartService;

    public ServicePartController(ServicePartService servicePartService) {
        this.servicePartService = servicePartService;
    }

    @PostMapping
    public ServicePart addServicePart(@RequestBody ServicePart servicePart) {
        return servicePartService.addServicePart(servicePart);
    }

    @GetMapping("/service-entry/{serviceEntryId}")
    public List<ServicePart> getPartsByServiceEntry(@PathVariable Long serviceEntryId) {
        return servicePartService.getPartsByServiceEntry(serviceEntryId);
    }
}
