package com.motorcycle.areas.supplier.servicesImpl;

import com.motorcycle.areas.supplier.entities.Supplier;
import com.motorcycle.areas.supplier.services.SupplierService;
import com.motorcycle.areas.supplier.models.bindingModels.AddSupplierModel;
import com.motorcycle.areas.supplier.models.bindingModels.EditSupplierModel;
import com.motorcycle.areas.supplier.models.bindingModels.SupplierModel;
import com.motorcycle.areas.supplier.models.viewModels.SupplierView;
import com.motorcycle.areas.supplier.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<SupplierView> getAll() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<SupplierView> supplierViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Supplier supplier : suppliers) {
            SupplierView supplierView = modelMapper.map(supplier,SupplierView.class);
            supplierViews.add(supplierView);
        }
        return supplierViews;
    }

    @Override
    public SupplierModel getByName(String name) {
        Supplier supplier = this.supplierRepository.findFirstByName(name);
        ModelMapper modelMapper = new ModelMapper();
        SupplierModel supplierModel = null;
        if (supplier != null){
            supplierModel = modelMapper.map(supplier,SupplierModel.class);
        }

        return supplierModel;
    }

    @Override
    public void persist(AddSupplierModel supplierModel) {
        ModelMapper modelMapper = new ModelMapper();
        Supplier supplier = modelMapper.map(supplierModel,Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public EditSupplierModel getByIdToEdit(Long id) {
        Supplier supplier = this.supplierRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        EditSupplierModel editSupplierModel = null;
        if (supplier != null){
            editSupplierModel = modelMapper.map(supplier,EditSupplierModel.class);
        }
        return editSupplierModel;
    }

    @Override
    public void update(EditSupplierModel editSupplierModel) {
        Supplier supplier = this.supplierRepository.findOne(editSupplierModel.getId());
//        supplier.setImporter(editSupplierModel.getImporter());
        supplier.setName(editSupplierModel.getName());
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public void delete(EditSupplierModel editSupplierModel) {
        Supplier supplier = this.supplierRepository.findOne(editSupplierModel.getId());
        this.supplierRepository.delete(supplier);
    }


}
