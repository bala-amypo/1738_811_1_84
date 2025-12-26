package com.example.demo.service;

import com.example.demo.model.VerificationLog;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public interface VerificationLogService {
    VerificationLog createLog(VerificationLog log);
    VerificationLog getLogById(Long id) throws EntityNotFoundException;
    List<VerificationLog> getLogsForEntry(Long entryId);
}
