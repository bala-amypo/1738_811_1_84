package com.example.demo.service;

import com.example.demo.model.VerificationLog;

import java.util.List;

public interface VerificationLogService {
    VerificationLog saveLog(VerificationLog log);
    List<VerificationLog> getAllLogs();
    List<VerificationLog> getLogsByServiceEntryId(Long serviceEntryId);
    VerificationLog getLogById(Long id);
}
