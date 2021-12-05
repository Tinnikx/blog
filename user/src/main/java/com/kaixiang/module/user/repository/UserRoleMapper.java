package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.entity.UserRole;

import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface UserRoleMapper {

    UserRole findByUserUuid(UUID userUuid);

    void create(UserRole userRole);

    void deleteByUserUuid(UUID userUuid);
}
