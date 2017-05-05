package com.motorcycle.areas.user.controller;

import com.motorcycle.areas.user.models.bindingModels.LoginUser;
import com.motorcycle.areas.user.models.bindingModels.RegisterUser;
import com.motorcycle.areas.user.services.BasicUserService;
import com.motorcycle.errors.Errors;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private BasicUserService basicUserService;

    @GetMapping("register")
    public String getRegisterPage(@ModelAttribute RegisterUser registerUser, Model model) {
        model.addAttribute("view", "user/user-register");
        model.addAttribute("user", new RegisterUser());
        return "base-layout";
    }

//    @PostMapping("register")
//    public String registerUser(@ModelAttribute RegisterUser registerUser, RedirectAttributes redirectAttributes) {
//        List<String> errors = new ArrayList<>();
//        LoginUser loginUser = this.basicUserService.getByUsername(registerUser.getUsername());
//        if (!registerUser.getPassword().equals(registerUser.getConfirmPassword())) {
//            errors.add("Passwords mismatch");
//        }
//
//        if (loginUser != null){
//            errors.add("Username already exist");
//        }
//
//        if (errors.size() > 0){
//            redirectAttributes.addFlashAttribute("errors", errors);
//            return "redirect:/user/register";
//        }
//
//        this.basicUserService.register(registerUser);
//        return "redirect:/user/login";
//    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterUser registerUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/user/user-register");
            return "base-layout";
        }

        List<String> errors = new ArrayList<>();
        LoginUser loginUser = this.basicUserService.getByUsername(registerUser.getUsername());

        if (loginUser == null) {
            this.basicUserService.register(registerUser);
            return "redirect:/user/login    ";
        }

        return "redirect:/";
    }


    @GetMapping("login")
    public String getLoginPage(Model model) {

        model.addAttribute("view", "user/user-login");
        model.addAttribute("user", new LoginUser());

        return "base-layout";
    }


    @PostMapping("login")
    public String getLoginUser(@ModelAttribute LoginUser loginUser, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        LoginUser user = this.basicUserService.getByUsername(loginUser.getUsername());
        List<String> errors = new ArrayList<>();

        // System.out.println(loggedUser.getUsername());
        if (user == null) {
            errors.add("Wrong username or password! Please try again!");
            redirectAttributes.addFlashAttribute("errors", errors);

            return "redirect:/user/login";
        }
        httpSession.setAttribute("user", user);

        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        model.addAttribute("view", "contacts");
        return "base-layout";
    }


}
