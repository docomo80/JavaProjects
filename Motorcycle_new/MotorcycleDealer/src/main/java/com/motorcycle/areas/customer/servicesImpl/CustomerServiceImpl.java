package com.motorcycle.areas.customer.servicesImpl;

import com.motorcycle.areas.customer.entities.Customer;
import com.motorcycle.areas.customer.services.CustomerService;
import com.motorcycle.areas.customer.models.bindingModels.AddCustomerModel;
import com.motorcycle.areas.customer.models.bindingModels.EditCustomerModel;
import com.motorcycle.areas.customer.models.bindingModels.RelatedCustomerModel;
import com.motorcycle.areas.customer.models.viewModels.CustomerDetailsView;
import com.motorcycle.areas.customer.models.viewModels.CustomerDriverView;
import com.motorcycle.areas.customer.models.viewModels.CustomerNameView;
import com.motorcycle.areas.customer.models.viewModels.CustomerView;
import com.motorcycle.areas.customer.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void persist(AddCustomerModel addCustomerModel) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(addCustomerModel, Customer.class);
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<CustomerView> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        customers = this.customerRepository.getAllCustomers();

        ModelMapper modelMapper = new ModelMapper();
        List<CustomerView> customerViews = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerView customerView = modelMapper.map(customer, CustomerView.class);
            customerViews.add(customerView);
        }

        return customerViews;
    }

    @Override
    public CustomerDetailsView getById(Long id) {
        Customer customer = this.customerRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        CustomerDetailsView customerDetailsView = null;
        if (customer != null) {
            customerDetailsView = modelMapper.map(customer, CustomerDetailsView.class);
            final Double[] totalSum = {0D};
            CustomerDetailsView finalCustomerDetailsView = customerDetailsView;
            customer.getSales().forEach(sale -> {
                final double[] sum = {0};
                sale.getMotorcycle().getParts().stream().forEach(part -> {
                    sum[0] += part.getPrice();
                });

//                totalSum[0] += sum[0] * (1 - (sale.getDiscount() + (finalCustomerDetailsView.getYoungDriver() ? 0.05 : 0)));
            });
            customerDetailsView.setTotalSum(totalSum[0]);
        }
        return customerDetailsView;
    }

    @Override
    public EditCustomerModel getByIdToEdit(Long id) {
        Customer customer = this.customerRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        EditCustomerModel customerModel = null;
        if (customer != null) {
            customerModel = modelMapper.map(customer, EditCustomerModel.class);

        }
        return customerModel;
    }

    @Override
    public void update(EditCustomerModel customerModel) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerModel, Customer.class);
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<CustomerNameView> getAll() {
        List<Customer> customers = this.customerRepository.findAll();
        List<CustomerNameView> customerNameViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Customer customer : customers) {
            CustomerNameView customerNameView = modelMapper.map(customer, CustomerNameView.class);
            customerNameViews.add(customerNameView);
        }
        return customerNameViews;
    }

    @Override
    public RelatedCustomerModel getByName(String name) {
        Customer customer = this.customerRepository.findFirstByName(name);
        ModelMapper modelMapper = new ModelMapper();
        RelatedCustomerModel relatedCustomerModel = null;
        if (customer != null) {
            relatedCustomerModel = modelMapper.map(customer, RelatedCustomerModel.class);

        }
        return relatedCustomerModel;
    }

    @Override
    public CustomerDriverView getDriverById(Long id) {
        Customer customer = this.customerRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        CustomerDriverView customerDriverView = null;
        if (customer != null) {
            customerDriverView = modelMapper.map(customer, CustomerDriverView.class);
        }
        return customerDriverView;
    }
}
