package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.repository.entity.RequestPermission;

import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/2
 */
@Mapper
public interface RequestPermissionMapper {

    void create(RequestPermission requestPermission);

    void deleteAllByUserUuid(UUID userUuid);
}
