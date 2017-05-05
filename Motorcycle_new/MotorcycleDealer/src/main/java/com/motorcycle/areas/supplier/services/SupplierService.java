package com.motorcycle.areas.supplier.services;

import com.motorcycle.areas.supplier.models.bindingModels.AddSupplierModel;
import com.motorcycle.areas.supplier.models.bindingModels.EditSupplierModel;
import com.motorcycle.areas.supplier.models.bindingModels.SupplierModel;
import com.motorcycle.areas.supplier.models.viewModels.SupplierView;

import java.util.List;

public interface SupplierService {

    List<SupplierView> getAll();

    SupplierModel getByName(String name);

    void persist(AddSupplierModel supplierModel);

    EditSupplierModel getByIdToEdit(Long id);

    void update(EditSupplierModel editSupplierModel);

    void delete(EditSupplierModel editSupplierModel);
}
