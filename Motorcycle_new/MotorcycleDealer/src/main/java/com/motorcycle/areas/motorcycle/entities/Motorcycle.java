package com.motorcycle.areas.motorcycle.entities;

import com.motorcycle.areas.part.entities.Part;
import com.motorcycle.areas.sale.entities.Sale;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "motorcycle")
public class Motorcycle {

    private Long id;

    private String make;

    private String model;

    private Double travelledDistance;

    private Set<Sale> sales;

    private Set<Part> parts;

    public Motorcycle() {
        this.sales = new HashSet<>();
        this.parts = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "motorcycle")
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    @ManyToMany()
    @JoinTable(name = "parts_motorcycle",
            joinColumns = @JoinColumn(name = "motorcycle_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id",referencedColumnName = "id"))
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
