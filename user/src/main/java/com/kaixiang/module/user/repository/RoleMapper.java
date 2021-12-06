package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.repository.entity.Role;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    Role find(Integer id);

    Role findByName(String name);

    Role findById(Integer id);
}
