package com.motorcycle.areas.part.servicesImpl;

import com.motorcycle.areas.part.entities.Part;
import com.motorcycle.areas.part.services.PartService;
import com.motorcycle.areas.part.models.bindingModels.AddPartModel;
import com.motorcycle.areas.part.models.bindingModels.EditPartModel;
import com.motorcycle.areas.part.models.bindingModels.PartModel;
import com.motorcycle.areas.part.models.viewModels.PartView;
import com.motorcycle.areas.part.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepository partRepository;

    @Override
    public void persist(AddPartModel partModel) {
        ModelMapper modelMapper = new ModelMapper();
        Part part = modelMapper.map(partModel, Part.class);
        this.partRepository.saveAndFlush(part);
    }

    @Override
    public List<PartView> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Part> parts = this.partRepository.findAll();
        List<PartView> partViews = new ArrayList<>();
        for (Part part : parts) {
            PartView partView = modelMapper.map(part, PartView.class);
            partViews.add(partView);
        }

        return partViews;
    }

    @Override
    public EditPartModel getById(Long id) {
        Part part = this.partRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        EditPartModel editPartModel = null;
        if (part != null) {
            editPartModel = modelMapper.map(part, EditPartModel.class);
        }
        return editPartModel;
    }

    @Override
    public void update(EditPartModel partModel) {
        this.partRepository.update(partModel.getPrice(), partModel.getQuantity(), partModel.getId());
    }

    @Override
    public PartView getViewById(Long id) {
        Part part = this.partRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        PartView partView = null;
        if (part != null) {
            partView = modelMapper.map(part, PartView.class);
        }
        return partView;
    }

    @Override
    public void delete(EditPartModel editPartModel) {
        this.partRepository.delete(editPartModel.getId());
    }

    @Override
    public PartModel getByName(String name) {
        Part part = this.partRepository.findByName(name);
        ModelMapper modelMapper = new ModelMapper();
        PartModel partModel = null;
        if (part != null) {
            partModel = modelMapper.map(part, PartModel.class);
        }
        return partModel;
    }
}
