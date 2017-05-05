package com.motorcycle.areas.controllers;

import com.motorcycle.areas.customer.models.viewModels.CustomerView;
import com.motorcycle.areas.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("view", "home");
        return "base-layout";
    }

//    @GetMapping("/error")
//    public String getErrorPage(){
//        return "error";
//    }

    @GetMapping("test")
    public String getTest(Model model) {
//        model.addAttribute("view", "test");
        return "json";
    }

}
