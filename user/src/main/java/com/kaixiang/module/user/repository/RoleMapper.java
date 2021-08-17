package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.entity.Role;
import com.kaixiang.module.user.entity.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    Role find(Integer id);

    Role findByName(String name);
}
