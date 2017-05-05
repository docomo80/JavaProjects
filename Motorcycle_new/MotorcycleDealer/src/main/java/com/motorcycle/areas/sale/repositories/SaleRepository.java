package com.motorcycle.areas.sale.repositories;

import com.motorcycle.areas.sale.entities.Sale;
import com.motorcycle.areas.sale.models.viewModels.SaleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select s from Sale as s")
    List<SaleView> getAll();
}
