package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.ServicePartEntity;

@Repository
public interface ServicePartRepository extends JpaRepository<ServicePartEntity, Long> {
}
