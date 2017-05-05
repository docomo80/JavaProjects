package com.motorcycle.areas.sale.models.viewModels;

import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleWithPartsView;
import com.motorcycle.areas.customer.models.viewModels.CustomerDriverView;

public class FinalizeSaleView {

    private MotorcycleWithPartsView motorcycle;

    private CustomerDriverView customer;

    private Double motorcyclePrice;

    private Double finalMotorcyclePrice;

    public MotorcycleWithPartsView getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(MotorcycleWithPartsView motorcycle) {
        this.motorcycle = motorcycle;
    }

    public CustomerDriverView getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDriverView customer) {
        this.customer = customer;
    }

    public Double getMotorcyclePrice() {
        return motorcyclePrice;
    }

    public void setMotorcyclePrice(Double motorcyclePrice) {
        this.motorcyclePrice = motorcyclePrice;
    }

    public Double getFinalMotorcyclePrice() {
        return finalMotorcyclePrice;
    }

    public void setFinalMotorcyclePrice(Double finalMotorcyclePrice) {
        this.finalMotorcyclePrice = finalMotorcyclePrice;
    }
}
