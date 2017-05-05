package com.motorcycle.areas.supplier.repositories;

import com.motorcycle.areas.supplier.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("select s from Supplier as s")
    List<Supplier> getAll();

    Supplier findFirstByName(String name);
}
