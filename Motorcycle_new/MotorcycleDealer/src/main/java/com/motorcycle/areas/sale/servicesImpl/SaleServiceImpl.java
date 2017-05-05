package com.motorcycle.areas.sale.servicesImpl;

import com.motorcycle.areas.sale.entities.Sale;
import com.motorcycle.areas.sale.services.SaleService;
import com.motorcycle.areas.sale.models.bindingModels.SaleModel;
import com.motorcycle.areas.sale.models.viewModels.SaleView;
import com.motorcycle.areas.sale.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<SaleView> getAll() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleView> saleViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Sale sale : sales) {
            SaleView saleView = modelMapper.map(sale, SaleView.class);
            saleViews.add(saleView);
        }
        return saleViews;
    }

    @Override
    public SaleView getById(Long id) {
        Sale sale = this.saleRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        SaleView saleView = null;
        if (sale != null) {
            saleView = modelMapper.map(sale, SaleView.class);
        }
        return saleView;
    }


    @Override
    public void persist(SaleModel saleModel) {
        ModelMapper modelMapper = new ModelMapper();
        Sale sale = modelMapper.map(saleModel, Sale.class);
        this.saleRepository.saveAndFlush(sale);
    }
}


