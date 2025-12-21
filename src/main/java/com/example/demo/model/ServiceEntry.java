package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ServiceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Garage garage;

    private String serviceType;

    private LocalDate serviceDate;

    private Integer odometerReading;

    private String description;

    private LocalDateTime recordedAt;

    public ServiceEntry() {
    }

    public ServiceEntry(
            Vehicle vehicle,
            Garage garage,
            String serviceType,
            LocalDate serviceDate,
            Integer odometerReading,
            String description,
            LocalDateTime recordedAt
    ) {
        this.vehicle = vehicle;
        this.garage = garage;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.odometerReading = odometerReading;
        this.description = description;
        this.recordedAt = recordedAt;
    }

    public Long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Garage getGarage() {
        return garage;
    }

    public String getServiceType() {
        return serviceType;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public Integer getOdometerReading() {
        return odometerReading;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
}
