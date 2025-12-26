package com.example.demo.repository;

import com.example.demo.model.VerificationLog;
import com.example.demo.model.ServiceEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationLogRepository extends JpaRepository<VerificationLog, Long> {

    Optional<VerificationLog> findByServiceEntry(ServiceEntry serviceEntry);
}
