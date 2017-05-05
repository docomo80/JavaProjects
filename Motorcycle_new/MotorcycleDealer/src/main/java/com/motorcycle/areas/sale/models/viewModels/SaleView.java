package com.motorcycle.areas.sale.models.viewModels;

import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleView;
import com.motorcycle.areas.customer.models.viewModels.CustomerNameView;

public class SaleView {

    private Long id;

    private MotorcycleView motorcycle;

    private CustomerNameView customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MotorcycleView getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(MotorcycleView motorcycle) {
        this.motorcycle = motorcycle;
    }

    public CustomerNameView getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerNameView customer) {
        this.customer = customer;
    }
}
