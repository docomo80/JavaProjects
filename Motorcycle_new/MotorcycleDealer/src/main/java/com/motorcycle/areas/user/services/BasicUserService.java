package com.motorcycle.areas.user.services;

import com.motorcycle.areas.user.entities.User;
import com.motorcycle.areas.user.models.bindingModels.LoggedUser;
import com.motorcycle.areas.user.models.bindingModels.LoginUser;
import com.motorcycle.areas.user.models.bindingModels.RegisterUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface BasicUserService extends UserDetailsService {

    void register(RegisterUser RegisterUser);

    LoggedUser getByUsernameAndPassword(String username, String password);

    LoginUser getByUsername(String username);

    List<User> getAllUsers();
}