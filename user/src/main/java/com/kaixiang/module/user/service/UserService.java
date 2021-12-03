package com.kaixiang.module.user.service;

import com.kaixiang.module.user.entity.User;
import com.kaixiang.module.user.repository.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public Boolean checkIfExist(String email, String nickName) {
        return userMapper.countByEmailOrNickName(email, nickName) != 0;
    }

    public User findByUuid(String uuid) {
        return userMapper.findByUuid(uuid);
    }

    public Boolean checkEmailExist(String email) {
        return false;
    }

    public Boolean checkNicknameExist(String nickname) {
        return false;
    }

    @Transactional
    public void create(User user) {
        userMapper.create(user);
    }
}
