package com.motorcycle.areas.part.entities;

import com.motorcycle.areas.motorcycle.entities.Motorcycle;
import com.motorcycle.areas.supplier.entities.Supplier;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part {

    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private Supplier supplier;

    private Set<Motorcycle> motorcycles;

    public Part() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ManyToOne()
    @JoinColumn(name = "supplier_id",referencedColumnName = "id")
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {

        this.supplier = supplier;
    }

    @ManyToMany(mappedBy = "parts",cascade = CascadeType.ALL)
    public Set<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(Set<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }

}
