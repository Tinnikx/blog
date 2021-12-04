package com.kaixiang.module.user;

import com.kaixiang.module.common.config.EnableCommonLib;
import com.kaixiang.module.db.configuration.EnableDB;
import com.kaixiang.security.configuration.EnableSecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableDB
@EnableSecurity
@EnableCommonLib
@EnableCaching
@MapperScan(basePackages = "com.kaixiang.module.user.repository")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
