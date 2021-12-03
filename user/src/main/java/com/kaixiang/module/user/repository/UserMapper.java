package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.entity.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface UserMapper {

    User find(Integer id);

    User findByEmail(String email);

    void create(User user);

    User findByUuid(UUID uuid);

    Integer countByEmailOrNickName(String email, String nickName);

    void delete(UUID uuid);
}
