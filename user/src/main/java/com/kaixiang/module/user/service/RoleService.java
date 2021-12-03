package com.kaixiang.module.user.service;

import com.kaixiang.module.user.entity.Role;
import com.kaixiang.module.user.repository.RoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role findByRoleName(String defaultRole) {
        return roleMapper.findByName(defaultRole);
    }

    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    public void deleteByUserUuid(UUID userUuid) {
        roleMapper.deleteByUserUuid(userUuid);
    }
}
