package com.motorcycle.areas.customer.services;

import com.motorcycle.areas.customer.entities.Customer;
import com.motorcycle.areas.customer.models.bindingModels.AddCustomerModel;
import com.motorcycle.areas.customer.models.bindingModels.EditCustomerModel;
import com.motorcycle.areas.customer.models.bindingModels.RelatedCustomerModel;
import com.motorcycle.areas.customer.models.viewModels.CustomerDetailsView;
import com.motorcycle.areas.customer.models.viewModels.CustomerDriverView;
import com.motorcycle.areas.customer.models.viewModels.CustomerNameView;
import com.motorcycle.areas.customer.models.viewModels.CustomerView;

import java.util.List;

public interface CustomerService {

    void persist(AddCustomerModel addCustomerModel);

    List<CustomerView> getAllCustomers();

    CustomerDetailsView getById(Long id);

    EditCustomerModel getByIdToEdit(Long id);

    void update(EditCustomerModel customerModel);

    List<CustomerNameView> getAll();

    RelatedCustomerModel getByName(String name);

    CustomerDriverView getDriverById(Long id);
}
