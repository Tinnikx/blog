package com.kaixiang.security.utils;

import com.kaixiang.module.common.exception.RecordNotFoundException;
import com.kaixiang.security.auth.dto.AuthenticatedUserDto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/3
 */
public class AuthUtils {

    public static AuthenticatedUserDto getCurrentUser() throws RecordNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication)
            .map(Authentication::getPrincipal)
            .filter(principal -> principal instanceof AuthenticatedUserDto)
            .map(principal -> (AuthenticatedUserDto) principal)
            .orElseThrow(() -> new RecordNotFoundException("current user not found"));
    }
}
