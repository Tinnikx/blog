package com.kaixiang.security.auth;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kaixiang Tao
 * @date 2019/12/5
 */
@Component
public class StandardAuthenticationDataSource implements AuthenticationDetailsSource<HttpServletRequest, StandardAuthenticationDetails> {
    @Override public StandardAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new StandardAuthenticationDetails();
    }
}
