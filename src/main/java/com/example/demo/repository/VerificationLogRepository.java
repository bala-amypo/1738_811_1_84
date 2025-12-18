package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.VerificationLogEntity;

@Repository
public interface VerificationLogRepository extends JpaRepository<VerificationLogEntity, Long> {
}
