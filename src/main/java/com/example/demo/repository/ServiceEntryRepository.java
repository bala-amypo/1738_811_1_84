package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.ServiceEntryEntity;

@Repository
public interface ServiceEntryRepository extends JpaRepository<ServiceEntryEntity, Long> {
}
