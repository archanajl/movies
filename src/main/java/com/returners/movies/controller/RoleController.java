package com.returners.movies.controller;

import com.returners.movies.model.DataResponse;
import com.returners.movies.model.Role;
import com.returners.movies.service.RoleService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<DataResponse> getAllRoles(){
        List<Role> roles = roleService.getAllRoles();
        return ResponseUtil.getSuccessResponse(roles,"All roles returned successfully");
    }

    @PutMapping(
            value = "/roles/user/{userId}/roles/{roleId}")
    public ResponseEntity<DataResponse> setUserRoles(@PathVariable Long userId, @PathVariable Long roleId) {
        roleService.addRoleToUser(userId,roleId);
        return ResponseUtil.getSuccessResponse(null,"Role set for  " + userId) ;

    }

    @PostMapping(
            value = "/")
    public ResponseEntity<DataResponse> addRole(@RequestBody Role requestBody ) {
        Role role = roleService.addRole(requestBody);
        return ResponseUtil.getSuccessResponse(role,"Role added successfully!") ;

    }
}