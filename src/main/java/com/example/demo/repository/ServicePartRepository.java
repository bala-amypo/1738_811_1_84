package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ServicePart;

public interface ServicePartRepository extends JpaRepository<ServicePart, Long> {

    List<ServicePart> findByServiceEntryId(Long serviceEntryId);
}
