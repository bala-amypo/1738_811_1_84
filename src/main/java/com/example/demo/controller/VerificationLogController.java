package com.example.demo.controller;

import com.example.demo.model.VerificationLog;
import com.example.demo.service.VerificationLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verification-logs")
public class VerificationLogController {

    private final VerificationLogService verificationLogService;

    public VerificationLogController(VerificationLogService verificationLogService) {
        this.verificationLogService = verificationLogService;
    }

    @PostMapping
    public VerificationLog addLog(@RequestBody VerificationLog verificationLog) {
        return verificationLogService.addVerificationLog(verificationLog);
    }

    @GetMapping("/service-entry/{serviceEntryId}")
    public List<VerificationLog> getLogsByServiceEntry(@PathVariable Long serviceEntryId) {
        return verificationLogService.getLogsByServiceEntry(serviceEntryId);
    }
}
