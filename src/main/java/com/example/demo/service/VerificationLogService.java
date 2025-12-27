package com.example.demo.service;

import com.example.demo.model.VerificationLog;

import java.util.List;

public interface VerificationLogService {

    VerificationLog addVerificationLog(VerificationLog verificationLog);

    List<VerificationLog> getLogsByServiceEntry(Long serviceEntryId);
}
