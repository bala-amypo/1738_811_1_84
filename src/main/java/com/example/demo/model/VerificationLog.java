package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_entry_id", nullable = false)
    private ServiceEntry serviceEntry;

    private LocalDateTime verifiedAt;

    private Boolean verifiedBySystem = true;

    private String notes;

    public VerificationLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceEntry getServiceEntry() {
        return serviceEntry;
    }

    public void setServiceEntry(ServiceEntry serviceEntry) {
        this.serviceEntry = serviceEntry;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public Boolean getVerifiedBySystem() {
        return verifiedBySystem;
    }

    public void setVerifiedBySystem(Boolean verifiedBySystem) {
        this.verifiedBySystem = verifiedBySystem;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
