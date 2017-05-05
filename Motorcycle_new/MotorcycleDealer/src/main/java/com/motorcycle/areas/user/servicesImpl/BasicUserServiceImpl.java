package com.motorcycle.areas.user.servicesImpl;

import com.motorcycle.areas.errors.Errors;
import com.motorcycle.areas.role.entities.Role;
import com.motorcycle.areas.user.entities.BasicUser;
import com.motorcycle.areas.user.entities.User;
import com.motorcycle.areas.user.services.BasicUserService;
import com.motorcycle.areas.user.models.bindingModels.LoggedUser;
import com.motorcycle.areas.user.models.bindingModels.LoginUser;
import com.motorcycle.areas.user.models.bindingModels.RegisterUser;
import com.motorcycle.areas.user.repositories.BasicUserRepository;
import com.motorcycle.areas.role.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BasicUserServiceImpl implements BasicUserService {

    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private  BasicUserRepository userRepository;

    @Autowired
    private  RoleService roleService;

    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public void register(RegisterUser registerUser) {
        BasicUser user = this.modelMapper.map(registerUser, BasicUser.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registerUser.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        if (getAllUsers().size() == 0){
            Role role = new Role();
            role.setAuthority("ROLE_ADMIN");
            user.addRole(role);
        } else {
            Role role = new Role();
            role.setAuthority("ROLE_USER");
            user.addRole(role);
        }

        this.userRepository.save(user);
    }

    @Transactional
    @Override
    public LoggedUser getByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);

        //System.out.println(user.getUsername() + user.getPassword());

        ModelMapper modelMapper = new ModelMapper();
        LoggedUser loggedUser = null;
        if (user != null) {
            loggedUser = modelMapper.map(user, LoggedUser.class);
        }

        return loggedUser;
    }

    @Override
    public LoginUser getByUsername(String username) {
        User user = this.userRepository.findOneByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        LoginUser loginUser = null;
        if (user != null) {
            loginUser = modelMapper.map(user, LoginUser.class);
        }
        return loginUser;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }
}
