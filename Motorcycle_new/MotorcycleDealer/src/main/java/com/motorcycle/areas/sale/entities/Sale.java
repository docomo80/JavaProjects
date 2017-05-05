package com.motorcycle.areas.sale.entities;

import com.motorcycle.areas.customer.entities.Customer;
import com.motorcycle.areas.motorcycle.entities.Motorcycle;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

    private Long id;

    private Motorcycle motorcycle;

    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "motorcycle_id",referencedColumnName = "id")
    public Motorcycle getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(Motorcycle motorcycle) {
        this.motorcycle = motorcycle;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
