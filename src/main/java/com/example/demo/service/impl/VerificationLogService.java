package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationLogService;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    @Autowired
    VerificationLogRepository repo;

    @Override
    public VerificationLog createLog(VerificationLog log) {
        return repo.save(log);
    }

    @Override
    public List<VerificationLog> getLogsForEntry(Long entryId) {
        return repo.findByServiceEntryId(entryId);
    }

    @Override
    public VerificationLog getLogById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
