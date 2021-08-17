package com.kaixiang.module.user.controller;

import com.kaixiang.module.user.repository.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
//    @PreAuthorize("hasPermission('123', '456')")
    public String test() {
        return userMapper.find(1).toString();
    }
}
