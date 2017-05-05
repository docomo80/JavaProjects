package com.motorcycle.areas.motorcycle.services;

import com.motorcycle.areas.motorcycle.entities.Motorcycle;
import com.motorcycle.areas.motorcycle.models.bindingModels.MotorcycleModel;
import com.motorcycle.areas.motorcycle.models.bindingModels.RelatedMotorcycleModel;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleInfoView;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleView;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleWithPartsView;

import java.util.List;

public interface MotorcycleService {

    void persist(MotorcycleModel motorcycleModel);

    MotorcycleWithPartsView getById(Long id);

    List<MotorcycleInfoView> getAll();

    RelatedMotorcycleModel getByMake(String name);

}
