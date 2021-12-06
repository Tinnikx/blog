package com.kaixiang.module.user.controller;

import com.kaixiang.module.user.repository.UserMapper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.Collections;

@RestController
public class TestController implements ApplicationContextAware {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
//    @PreAuthorize("hasPermission('123', '456')")
    public String test() {
        return userMapper.find(1).toString();
    }

    @GetMapping("/test-role")
    @PreAuthorize("hasAnyRole('ROLE_STANDARD')")
    public String testRole() {
        return "success";
    }

    @GetMapping("/test1")
    public String test1() {
        throw new MethodNotAllowedException("GET", Collections.singleton(HttpMethod.POST));
    }

    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println();
    }
}
