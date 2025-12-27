package com.example.demo.service.impl;

import com.example.demo.model.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    private final VerificationLogRepository verificationLogRepository;

    public VerificationLogServiceImpl(VerificationLogRepository verificationLogRepository) {
        this.verificationLogRepository = verificationLogRepository;
    }

    @Override
    public VerificationLog addVerificationLog(VerificationLog verificationLog) {
        return verificationLogRepository.save(verificationLog);
    }

    @Override
    public List<VerificationLog> getLogsByServiceEntry(Long serviceEntryId) {
        return verificationLogRepository.findByServiceEntryId(serviceEntryId);
    }
}
