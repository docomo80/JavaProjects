package com.motorcycle.areas.sale.controller;

import com.motorcycle.areas.logs.models.bindingModels.LogModel;
import com.motorcycle.areas.logs.services.LogService;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleInfoView;
import com.motorcycle.enums.Operation;
import com.motorcycle.areas.customer.models.bindingModels.RelatedCustomerModel;

import com.motorcycle.areas.motorcycle.models.bindingModels.RelatedMotorcycleModel;
import com.motorcycle.areas.sale.models.bindingModels.SaleModel;
import com.motorcycle.areas.user.models.bindingModels.LoggedUser;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleView;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleWithPartsView;
import com.motorcycle.areas.customer.models.viewModels.CustomerDriverView;
import com.motorcycle.areas.customer.models.viewModels.CustomerNameView;
import com.motorcycle.areas.sale.models.viewModels.FinalizeSaleView;
import com.motorcycle.areas.sale.models.viewModels.SaleView;
import com.motorcycle.areas.motorcycle.services.MotorcycleService;
import com.motorcycle.areas.customer.services.CustomerService;

import com.motorcycle.areas.sale.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MotorcycleService motorcycleService;

    @Autowired
    private LogService logService;

    @GetMapping("all")
    public String getAllSalesPage(Model model) {
        List<SaleView> saleViews = this.saleService.getAll();
        model.addAttribute("sales", saleViews);
        model.addAttribute("view", "/sales/all-sales");
        return "base-layout";
    }


    @GetMapping("{id}")
    public String getSalePage(Model model, @PathVariable Long id) {
        SaleView saleView = this.saleService.getById(id);
        model.addAttribute("sale", saleView);
        model.addAttribute("view", "/sales/sale-details");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddSalePage(Model model) {
        List<MotorcycleInfoView> motorcycles = this.motorcycleService.getAll();
        List<CustomerNameView> customers = this.customerService.getAll();

        model.addAttribute("motorcycles", motorcycles);
        model.addAttribute("customers", customers);
        model.addAttribute("sale", new SaleModel());
        model.addAttribute("view", "sales/sale-add");

        return "base-layout";
    }

    @PostMapping("add")
    private String addSale(@ModelAttribute SaleModel saleModel,
                           @RequestParam String customerName,
                           @RequestParam String motorcycleName,
                           RedirectAttributes redirectAttributes) {
        RelatedMotorcycleModel relatedCarModel = this.motorcycleService.getByMake(motorcycleName);
        RelatedCustomerModel relatedCustomerModel = this.customerService.getByName(customerName);
        saleModel.setMotorcycle(relatedCarModel);
        saleModel.setCustomer(relatedCustomerModel);
        redirectAttributes.addFlashAttribute("sale", saleModel);
        return "redirect:/sales/add/finalize";
    }

    @GetMapping("add/finalize")
    public String getFinalizeSalePage(Model model) {
        SaleModel saleModel = (SaleModel) model.asMap().get("sale");
        MotorcycleWithPartsView motorcycleWithPartsView = this.motorcycleService.getById(saleModel.getMotorcycle().getId());
        CustomerDriverView customerDriverView = this.customerService.getDriverById(saleModel.getCustomer().getId());

        final double[] motocyclePrice = {0};
        motorcycleWithPartsView.getParts().stream().forEach(motorcycle -> motocyclePrice[0] += motorcycle.getPrice());
        double totalPrice = motocyclePrice[0];

        FinalizeSaleView finalizeSaleView = new FinalizeSaleView();
        // finalizeSaleView.setDiscount(saleModel.getDiscount());
        finalizeSaleView.setMotorcycle(motorcycleWithPartsView);
        finalizeSaleView.setCustomer(customerDriverView);
        finalizeSaleView.setMotorcyclePrice(motocyclePrice[0]);
        finalizeSaleView.setFinalMotorcyclePrice(totalPrice);

        model.addAttribute("sale", finalizeSaleView);
        model.addAttribute("view", "sales/sale-finalize");
        return "base-layout";
    }

    @PostMapping("add/finalize")
    public String finalizeSale(@ModelAttribute SaleModel saleModel,
                               @RequestParam String customerName,
                               @RequestParam String carName,
                               HttpSession httpSession) {
        RelatedMotorcycleModel relatedMotorcycleModel = this.motorcycleService.getByMake(carName);
        RelatedCustomerModel relatedCustomerModel = this.customerService.getByName(customerName);

        saleModel.setMotorcycle(relatedMotorcycleModel);
        saleModel.setCustomer(relatedCustomerModel);
        this.saleService.persist(saleModel);

        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        LogModel logModel = new LogModel(loggedUser, "Sales", Operation.ADD, new Date());
        this.logService.persist(logModel);
        return "redirect:/sales/all";
    }

    private List<SaleView> getSales() {
        List<SaleView> saleViews = new ArrayList<>();

        saleViews = this.saleService.getAll();

        return saleViews;
    }
}
