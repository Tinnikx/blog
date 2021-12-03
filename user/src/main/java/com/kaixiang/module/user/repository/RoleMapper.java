package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.entity.Role;

import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface RoleMapper {

    Role find(Integer id);

    Role findByName(String name);

    Role findById(Integer id);

    void deleteByUserUuid(UUID userUuid);
}
