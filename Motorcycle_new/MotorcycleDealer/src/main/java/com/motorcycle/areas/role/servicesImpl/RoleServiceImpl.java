package com.motorcycle.areas.role.servicesImpl;

import com.motorcycle.areas.role.entities.Role;
import com.motorcycle.areas.role.services.RoleService;
import com.motorcycle.areas.role.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
    }

    @Override
    public Role getAdminRole() {
        return this.roleRepository.findOneByAuthority(ADMIN_ROLE);
    }


}
