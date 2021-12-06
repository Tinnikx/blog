package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.UUID;

@Mapper
public interface UserMapper {

    User find(Integer id);

    User findByEmail(String email);

    void create(User user);

    User findByUuid(UUID uuid);

    Integer countByEmailOrNickName(@Param("email") String email, @Param("nickName") String nickName);

    void delete(UUID uuid);

    void update(User user);

}
