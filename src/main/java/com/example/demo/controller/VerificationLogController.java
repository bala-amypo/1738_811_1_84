package com.example.demo.controller;

import com.example.demo.model.VerificationLog;
import com.example.demo.service.VerificationLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verification-logs")
public class VerificationLogController {

    private final VerificationLogService logService;

    public VerificationLogController(VerificationLogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public VerificationLog addLog(@RequestBody VerificationLog log) {
        return logService.saveLog(log);
    }

    @GetMapping
    public List<VerificationLog> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/{id}")
    public VerificationLog getLog(@PathVariable Long id) {
        return logService.getLogById(id);
    }

    @GetMapping("/service-entry/{serviceEntryId}")
    public List<VerificationLog> getLogsByServiceEntry(@PathVariable Long serviceEntryId) {
        return logService.getLogsByServiceEntryId(serviceEntryId);
    }
}
