package com.returners.movies.service;

import com.returners.movies.model.Role;

import java.util.List;

public interface RoleService {

    Role addRole(Role role);
    void addRoleToUser(Long userId, Long roleId);
    List<Role> getAllRoles();
}
