package com.example.demo.service.impl;

import com.example.demo.model.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    private final VerificationLogRepository logRepository;

    public VerificationLogServiceImpl(VerificationLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public VerificationLog saveLog(VerificationLog log) {
        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getAllLogs() {
        return logRepository.findAll();
    }

    @Override
    public List<VerificationLog> getLogsByServiceEntryId(Long serviceEntryId) {
        return logRepository.findByServiceEntryId(serviceEntryId);
    }

    @Override
    public VerificationLog getLogById(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification log not found with id " + id));
    }
}
