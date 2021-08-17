package com.kaixiang.security.auth;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Kaixiang Tao
 * @date 2019/12/9
 */
@Component
public class StandardAuthenticationPermissionEvaluator implements PermissionEvaluator {
    @Override public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("testing .. ");
        return false;
    }

    @Override public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        System.out.println("testing ....22");
        return true;
    }
}
