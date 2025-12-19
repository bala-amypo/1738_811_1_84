package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.VerificationLog;

public interface VerificationLogRepository extends JpaRepository<VerificationLog, Long> {

    List<VerificationLog> findByServiceEntryId(Long serviceEntryId);
}
