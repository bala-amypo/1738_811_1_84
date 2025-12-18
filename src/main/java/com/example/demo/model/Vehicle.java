package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vin;
    private String make;
    private String model;
    private Integer year;
    private Long ownerId;
    private Boolean active;
    private Timestamp createdAt;

    // Parameterized Constructor
    public VehicleEntity(String vin, String make, String model, Integer year, Long ownerId) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.ownerId = ownerId;
        this.active = true;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Default Constructor
    public VehicleEntity() {
        this.active = true;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters (LIKE StudentEntity)

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVin() {
        return this.vin;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake() {
        return this.make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
}
