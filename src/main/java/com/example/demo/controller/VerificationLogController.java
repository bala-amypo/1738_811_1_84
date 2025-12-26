package com.example.demo.controller;

import com.example.demo.model.VerificationLog;
import com.example.demo.service.VerificationLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/verification-logs")
@Tag(name = "Verification Log Controller")
public class VerificationLogController {

    private final VerificationLogService verificationLogService;

    public VerificationLogController(VerificationLogService verificationLogService) {
        this.verificationLogService = verificationLogService;
    }

    @PostMapping
    public ResponseEntity<VerificationLog> createLog(@Valid @RequestBody VerificationLog log) {
        return ResponseEntity.ok(verificationLogService.createLog(log));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VerificationLog> getLog(@PathVariable Long id) {
        return ResponseEntity.ok(verificationLogService.getLogById(id));
    }

    @GetMapping("/entry/{entryId}")
    public ResponseEntity<List<VerificationLog>> getLogsForEntry(@PathVariable Long entryId) {
        return ResponseEntity.ok(verificationLogService.getLogsForEntry(entryId));
    }
}
