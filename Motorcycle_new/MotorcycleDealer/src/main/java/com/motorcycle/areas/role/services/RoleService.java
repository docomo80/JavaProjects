package com.motorcycle.areas.role.services;

import com.motorcycle.areas.role.entities.Role;

public interface RoleService {

    Role getDefaultRole();

    Role getAdminRole();
}

