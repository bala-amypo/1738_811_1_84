package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.VerificationLog;
import com.example.demo.service.VerificationLogService;

@RestController
@RequestMapping("/api/verification-logs")
public class VerificationLogController {

    @Autowired
    VerificationLogService ser;

    @PostMapping
    public VerificationLog createLog(@RequestBody VerificationLog log) {
        return ser.createLog(log);
    }

    @GetMapping("/{id}")
    public VerificationLog getLogById(@PathVariable Long id) {
        return ser.getLogById(id);
    }

    @GetMapping("/entry/{entryId}")
    public List<VerificationLog> getLogsForEntry(@PathVariable Long entryId) {
        return ser.getLogsForEntry(entryId);
    }
}
