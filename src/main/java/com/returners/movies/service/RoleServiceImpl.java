package com.returners.movies.service;

import com.returners.movies.model.Role;
import com.returners.movies.model.User;
import com.returners.movies.repository.RoleRepository;
import com.returners.movies.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);

    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        log.info("Add role to user");
        User user = userRepository.findById(userId).get();
        Role role = roleRepository.findById(roleId).get();
        user.getRoles().add(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
