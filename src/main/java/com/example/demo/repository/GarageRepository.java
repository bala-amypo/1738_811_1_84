package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.GarageEntity;

@Repository
public interface GarageRepository extends JpaRepository<GarageEntity, Long> {
}
