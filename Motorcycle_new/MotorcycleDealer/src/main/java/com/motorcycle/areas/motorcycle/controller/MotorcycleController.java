package com.motorcycle.areas.motorcycle.controller;

import com.motorcycle.areas.logs.models.bindingModels.LogModel;
import com.motorcycle.areas.logs.services.LogService;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleView;
import com.motorcycle.enums.Operation;
import com.motorcycle.areas.motorcycle.models.bindingModels.MotorcycleModel;
import com.motorcycle.areas.part.models.bindingModels.PartModel;
import com.motorcycle.areas.user.models.bindingModels.LoggedUser;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleInfoView;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleWithPartsView;
import com.motorcycle.areas.part.models.viewModels.PartView;
import com.motorcycle.areas.motorcycle.services.MotorcycleService;
import com.motorcycle.areas.part.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("motorcycle")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @Autowired
    private PartService partService;

    @Autowired
    private LogService logService;

    @GetMapping("all")
    public String getMotorcycleTablePage(Model model) {
        List<MotorcycleInfoView> motorcycleInfoViews = this.motorcycleService.getAll();
        model.addAttribute("motorcycles", motorcycleInfoViews);
        model.addAttribute("view", "/motorcycle/motorcycle-table");
        return "base-layout";
    }

    @GetMapping("{id}/parts")
    public String getMotorcyclePartsPage(Model model, @PathVariable Long id) {
        MotorcycleWithPartsView motorcycle = this.motorcycleService.getById(id);
        model.addAttribute("motorcycle", motorcycle);
        model.addAttribute("view", "/motorcycle/motorcycle-parts-table");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddMotorcyclePage(Model model) {
        List<PartView> partViews = this.partService.getAll();
        model.addAttribute("parts", partViews);
        model.addAttribute("motorcycle", new MotorcycleModel());
        model.addAttribute("view", "/motorcycle/motorcycle-add");

        return "base-layout";
    }

    @PostMapping("add")
    public String addMotorcyclePage(@ModelAttribute MotorcycleModel motorcycleModel, @RequestParam String[] partsNames, HttpSession httpSession) {
        Set<PartModel> partModels = new HashSet<>();
        for (String part : partsNames) {
            PartModel partModel = this.partService.getByName(part);
            partModels.add(partModel);
        }

        motorcycleModel.setParts(partModels);
        this.motorcycleService.persist(motorcycleModel);
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        LogModel logModel = new LogModel(loggedUser, "Motorcycle", Operation.ADD, new Date());
        this.logService.persist(logModel);
        return "redirect:/motorcycle/all";
    }
}
