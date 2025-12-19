package com.example.demo.model;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServiceEntry serviceEntry;

    private Timestamp verifiedAt =
            new Timestamp(System.currentTimeMillis());

    private Boolean verifiedBySystem = true;

    private String notes;

    public VerificationLog() {
    }

    public Long getId() {
        return id;
    }

    public ServiceEntry getServiceEntry() {
        return serviceEntry;
    }

    public void setServiceEntry(ServiceEntry serviceEntry) {
        this.serviceEntry = serviceEntry;
    }

    public Timestamp getVerifiedAt() {
        return verifiedAt;
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
