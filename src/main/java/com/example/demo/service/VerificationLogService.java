package com.example.demo.service;

import java.util.List;

import com.example.demo.model.VerificationLog;

public interface VerificationLogService {

    VerificationLog createLog(VerificationLog log);

    List<VerificationLog> getLogsForEntry(Long entryId);

    VerificationLog getLogById(Long id);
}
