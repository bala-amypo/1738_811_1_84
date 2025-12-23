package com.example.demo.service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import java.util.List;

public class VerificationLogServiceImpl implements VerificationLogService {

    private final VerificationLogRepository logRepository;

    public VerificationLogServiceImpl(VerificationLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public VerificationLog createLog(VerificationLog log) {
        return logRepository.save(log);
    }

    public List<VerificationLog> getLogsForEntry(Long entryId) {
        return logRepository.findByServiceEntryId(entryId);
    }

    public VerificationLog getLogById(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Log not found"));
    }
}
