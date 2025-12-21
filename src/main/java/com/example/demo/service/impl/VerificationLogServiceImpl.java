package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.VerificationLogService;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.model.VerificationLog;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    @Autowired
    private VerificationLogRepository repository;

    @Override
    public VerificationLog createLog(VerificationLog log) {

        log.setVerifiedAt(LocalDateTime.now());

        return repository.save(log);
    }

    @Override
    public VerificationLog getLogById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("VerificationLog not found"));
    }

    @Override
    public List<VerificationLog> getLogsForEntry(Long entryId) {

        return repository.findByServiceEntryId(entryId);
    }
}
