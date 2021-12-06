package com.kaixiang.module.user.service;

import com.kaixiang.module.common.exception.RecordNotFoundException;
import com.kaixiang.module.user.repository.entity.User;
import com.kaixiang.module.user.repository.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RequestPermissionService requestPermissionService;

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public Boolean checkIfExist(String email, String nickName) {
        return userMapper.countByEmailOrNickName(email, nickName) != 0;
    }

    @Transactional
    public void delAccount(UUID userUuid) {
        permissionService.deleteByUserUuid(userUuid);
        requestPermissionService.deleteAllByUserUuid(userUuid);
        userMapper.delete(userUuid);
    }

    public User findByUuid(UUID uuid) throws RecordNotFoundException {
        return Optional.ofNullable(userMapper.findByUuid(uuid)).orElseThrow(() -> new RecordNotFoundException("notFound"));
    }

    public Boolean checkEmailExist(String email) {
        return false;
    }

    public Boolean checkNicknameExist(String nickname) {
        return false;
    }

    public Boolean checkUserExistByUuid(UUID uuid) {
        return Optional.ofNullable(userMapper.findByUuid(uuid)).isPresent();
    }

    @Transactional
    public void create(User user) {
        userMapper.create(user);
    }

    @Transactional
    public void update(User user) {
        userMapper.update(user);
    }
}
