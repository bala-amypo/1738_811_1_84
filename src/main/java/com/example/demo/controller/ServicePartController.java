package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;

@RestController
@RequestMapping("/api/service-parts")
public class ServicePartController {

    @Autowired
    ServicePartService ser;

    @PostMapping
    public ServicePart addPart(@RequestBody ServicePart part) {
        return ser.createPart(part);
    }

    @GetMapping("/{id}")
    public ServicePart getPartById(@PathVariable Long id) {
        return ser.getPartById(id);
    }

    @GetMapping("/entry/{entryId}")
    public List<ServicePart> getPartsForEntry(@PathVariable Long entryId) {
        return ser.getPartsForEntry(entryId);
    }
}
