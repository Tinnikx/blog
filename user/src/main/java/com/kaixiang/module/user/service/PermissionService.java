package com.kaixiang.module.user.service;

import com.kaixiang.module.user.entity.Role;
import com.kaixiang.module.user.entity.UserRole;
import com.kaixiang.module.user.repository.UserRoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PermissionService {

    private static final String DEFAULT_ROLE = "ROLE_STANDARD";

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleService roleService;

    @Transactional
    public void assignStandardPermissionToUser(UUID userUuid) {
        Role role = roleService.findByRoleName(DEFAULT_ROLE);
        assignPermissionToUser(userUuid.toString(), role);
    }

    @Transactional
    public void assignPermissionToUser(String userUuid, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUserUuid(userUuid);
        userRole.setRoleId(role.getId());
        userRoleMapper.create(userRole);
    }

    @Transactional(readOnly = true)
    public String findPermissions(UUID userUuid) {
        UserRole userRole = userRoleMapper.findByUserUuid(userUuid);
        Role role = roleService.findById(userRole.getRoleId());
        return role.getName();
    }

    @Transactional
    public void deleteByUserUuid(UUID userUuid) {
        userRoleMapper.deleteByUserUuid(userUuid);
    }
}
