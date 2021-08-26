package com.kaixiang.security.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.MethodNotAllowedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kaixiang Tao
 * @date 2019/12/2
 */
@Component
public class StandardAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String SOURCE = "source";

    public StandardAuthenticationFilter(@Value("${security.auth.url}") String authUrl,
                                        AuthenticationManager authenticationManager,
                                        StandardAuthenticationSuccessfulHandler standardAuthenticationSuccessfulHandler,
                                        StandardAuthenticationFailureHandler standardAuthenticationFailureHandler,
                                        StandardAuthenticationDataSource standardAuthenticationDataSource) {
        super(authUrl);
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(standardAuthenticationSuccessfulHandler);
        this.setAuthenticationFailureHandler(standardAuthenticationFailureHandler);
        this.setAuthenticationDetailsSource(standardAuthenticationDataSource);
    }

    @Override public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals(HttpMethod.POST)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //        authentication.setAuthenticated(false);

        // test Authentication failure
//        throw new PreAuthenticatedCredentialsNotFoundException("test authentication failure");

//        return new UsernamePasswordAuthenticationToken("kaixiang",
//            "123", authorities);
        // 正确的授权方式是使用 AuthenticationManager 进行授权认证
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String source = request.getParameter(SOURCE);
        StandardAuthenticationToken authRequest = new StandardAuthenticationToken(username, password, source);
        setDetails(request, authRequest);
        return getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request, StandardAuthenticationToken authRequest) {
        // 此处可以在 security 配置文件中配置自己的 AuthenticationDetailsSource
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
