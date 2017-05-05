package com.motorcycle.areas.sale.services;

import com.motorcycle.areas.sale.models.bindingModels.SaleModel;
import com.motorcycle.areas.sale.models.viewModels.SaleView;

import java.util.List;

public interface SaleService {

    List<SaleView> getAll();

    SaleView getById(Long id);

    void persist(SaleModel saleModel);
}
