package com.motorcycle.areas.motorcycle.servicesImpl;

import com.motorcycle.areas.motorcycle.entities.Motorcycle;
import com.motorcycle.areas.motorcycle.services.MotorcycleService;
import com.motorcycle.areas.motorcycle.models.bindingModels.MotorcycleModel;
import com.motorcycle.areas.motorcycle.models.bindingModels.RelatedMotorcycleModel;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleInfoView;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleView;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleWithPartsView;
import com.motorcycle.areas.motorcycle.repositories.MotorcycleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MotorcycleServiceImpl implements MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;


    public void persist(MotorcycleModel motorcycleModel) {
        ModelMapper modelMapper = new ModelMapper();
        Motorcycle motorcycle = modelMapper.map(motorcycleModel, Motorcycle.class);
        this.motorcycleRepository.saveAndFlush(motorcycle);

    }

    @Override
    public List<MotorcycleInfoView> getAll() {
        List<Motorcycle> motorcycles = new ArrayList<>();
        motorcycles = this.motorcycleRepository.getAll();

        List<MotorcycleInfoView> motorcycleInfoViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Motorcycle motorcycle : motorcycles) {
            MotorcycleInfoView motorcycleInfoView = modelMapper.map(motorcycle, MotorcycleInfoView.class);
            motorcycleInfoViews.add(motorcycleInfoView);
        }
        return motorcycleInfoViews;
    }

    @Override
    public MotorcycleWithPartsView getById(Long id) {
        Motorcycle motorcycle = this.motorcycleRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        MotorcycleWithPartsView motorcycleWithPartsView = null;
        if (motorcycle != null) {
            motorcycleWithPartsView = modelMapper.map(motorcycle, MotorcycleWithPartsView.class);
        }
        return motorcycleWithPartsView;
    }


    @Override
    public RelatedMotorcycleModel getByMake(String name) {
        Motorcycle motorcycle = this.motorcycleRepository.findFirstByMake(name);
        ModelMapper modelMapper = new ModelMapper();
        RelatedMotorcycleModel relatedMotorcycleModel = null;
        if (motorcycle != null) {
            relatedMotorcycleModel = modelMapper.map(motorcycle, RelatedMotorcycleModel.class);
        }
        return relatedMotorcycleModel;
    }

}
