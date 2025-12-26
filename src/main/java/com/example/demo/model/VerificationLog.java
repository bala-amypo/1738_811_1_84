package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // ✅ prevents client from sending id
    private Long id;

    @ManyToOne(optional = false)
    private ServiceEntry serviceEntry;

    private LocalDateTime verifiedAt = LocalDateTime.now();

    private Boolean verifiedBySystem = true;

    private String notes;

    // getters and setters
    public Long getId() { return id; }

    public ServiceEntry getServiceEntry() { return serviceEntry; }
    public void setServiceEntry(ServiceEntry serviceEntry) { this.serviceEntry = serviceEntry; }

    public LocalDateTime getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }

    public Boolean getVerifiedBySystem() { return verifiedBySystem; }
    public void setVerifiedBySystem(Boolean verifiedBySystem) { this.verifiedBySystem = verifiedBySystem; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
