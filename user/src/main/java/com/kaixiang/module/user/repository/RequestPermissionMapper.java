package com.kaixiang.module.user.repository;

import com.kaixiang.module.user.entity.RequestPermission;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/2
 */
@Mapper
public interface RequestPermissionMapper {

    void create(RequestPermission requestPermission);
}
