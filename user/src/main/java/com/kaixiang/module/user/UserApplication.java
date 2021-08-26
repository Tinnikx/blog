package com.kaixiang.module.user;

import com.kaixiang.module.common.exception.config.EnableCommonLib;
import com.kaixiang.security.configuration.EnableSecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableSecurity
@EnableCommonLib
@EnableTransactionManagement
@MapperScan(basePackages = "com.kaixiang.module.user.repository")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
