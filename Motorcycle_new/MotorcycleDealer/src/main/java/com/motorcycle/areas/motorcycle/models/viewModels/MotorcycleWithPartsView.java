package com.motorcycle.areas.motorcycle.models.viewModels;

import com.motorcycle.areas.part.models.viewModels.PartView;

import java.util.Set;

public class MotorcycleWithPartsView {

    private Long id;

    private String make;

    private String model;

    private Double travelledDistance;

    private Set<PartView> parts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartView> getParts() {
        return parts;
    }

    public void setParts(Set<PartView> parts) {
        this.parts = parts;
    }
}
