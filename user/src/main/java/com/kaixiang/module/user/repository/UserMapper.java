package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.entity.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User find(Integer id);

    User findByEmail(String email);

    void create(User user);

    User findByUuid(String uuid);

    Integer countByEmailOrNickName(String email, String nickName);
}
