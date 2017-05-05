package com.motorcycle.areas.part.services;

import com.motorcycle.areas.part.models.bindingModels.AddPartModel;
import com.motorcycle.areas.part.models.bindingModels.EditPartModel;
import com.motorcycle.areas.part.models.bindingModels.PartModel;
import com.motorcycle.areas.part.models.viewModels.PartView;

import java.util.List;

public interface PartService {

    void persist(AddPartModel partModel);

    List<PartView> getAll();

    EditPartModel getById(Long id);

    void update(EditPartModel partModel);

    PartView getViewById(Long id);

    void delete(EditPartModel id);

    PartModel getByName(String name);
}
