package com.motorcycle.areas.supplier.controller;

import com.motorcycle.areas.logs.models.bindingModels.LogModel;
import com.motorcycle.areas.logs.services.LogService;
import com.motorcycle.enums.Operation;

import com.motorcycle.areas.supplier.models.bindingModels.AddSupplierModel;
import com.motorcycle.areas.supplier.models.bindingModels.EditSupplierModel;
import com.motorcycle.areas.user.models.bindingModels.LoggedUser;
import com.motorcycle.areas.supplier.models.viewModels.SupplierView;

import com.motorcycle.areas.supplier.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private LogService logService;

    @GetMapping("")
    public String getSuppliersPage(Model model, @RequestParam(required = false) String type) {
        Boolean importer = "Importers".equals(type);
        List<SupplierView> supplierViews = this.supplierService.getAll();
        model.addAttribute("suppliers", supplierViews);
        model.addAttribute("view", "/suppliers/suppliers-table");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddSupplierPage(Model model) {
        model.addAttribute("view", "/suppliers/supplier-modifiable");
        model.addAttribute("supplier", new AddSupplierModel());
        model.addAttribute("type", "Add");
        return "base-layout";
    }


    @PostMapping("add")
    public String addSupplier(@ModelAttribute AddSupplierModel supplierModel, HttpSession httpSession) {
        this.supplierService.persist(supplierModel);
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        LogModel logModel = new LogModel(loggedUser, "Suppliers", Operation.ADD, new Date());
        this.logService.persist(logModel);
        return "redirect:/suppliers";
    }

    @GetMapping("edit/{id}")
    public String getEditPage(Model model, @PathVariable Long id) {
        EditSupplierModel editSupplierModel = this.supplierService.getByIdToEdit(id);
        model.addAttribute("view", "/suppliers/supplier-modifiable");
        model.addAttribute("type", "Edit");
        model.addAttribute("supplier", editSupplierModel);
        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editSupplier(@ModelAttribute EditSupplierModel editSupplierModel, @PathVariable Long id, HttpSession httpSession) {
        editSupplierModel.setId(id);
        this.supplierService.update(editSupplierModel);
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        LogModel logModel = new LogModel(loggedUser, "Suppliers", Operation.EDIT, new Date());
        this.logService.persist(logModel);
        return "redirect:/suppliers";
    }

    @GetMapping("delete/{id}")
    public String getDeletePage(Model model, @PathVariable Long id) {
        EditSupplierModel editSupplierModel = this.supplierService.getByIdToEdit(id);
        model.addAttribute("view", "/suppliers/supplier-modifiable");
        model.addAttribute("type", "Delete");
        model.addAttribute("supplier", editSupplierModel);
        return "base-layout";
    }

    @PostMapping("delete/{id}")
    public String deleteSupplier(@ModelAttribute EditSupplierModel editSupplierModel, @PathVariable Long id, HttpSession httpSession) {
        editSupplierModel.setId(id);
        this.supplierService.delete(editSupplierModel);
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        LogModel logModel = new LogModel(loggedUser, "Suppliers", Operation.DELETE, new Date());
        this.logService.persist(logModel);
        return "redirect:/suppliers";
    }
}
