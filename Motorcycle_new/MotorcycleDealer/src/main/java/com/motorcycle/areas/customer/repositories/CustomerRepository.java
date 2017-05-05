package com.motorcycle.areas.customer.repositories;

import com.motorcycle.areas.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer as c")
    List<Customer> getAllCustomers();

    Customer findFirstByName(String name);
}
