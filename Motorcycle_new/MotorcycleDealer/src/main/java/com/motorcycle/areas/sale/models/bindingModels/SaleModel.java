package com.motorcycle.areas.sale.models.bindingModels;

import com.motorcycle.areas.customer.models.bindingModels.RelatedCustomerModel;
import com.motorcycle.areas.motorcycle.models.bindingModels.RelatedMotorcycleModel;

public class SaleModel {

    private RelatedCustomerModel customer;

    private RelatedMotorcycleModel motorcycle;

    public RelatedCustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(RelatedCustomerModel customer) {
        this.customer = customer;
    }

    public RelatedMotorcycleModel getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(RelatedMotorcycleModel motorcycle) {
        this.motorcycle = motorcycle;
    }

}
