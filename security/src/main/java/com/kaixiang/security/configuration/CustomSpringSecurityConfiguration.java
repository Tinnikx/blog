package com.kaixiang.security.configuration;

import com.kaixiang.security.auth.StandardAuthenticationPermissionEvaluator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import java.util.Arrays;

@Configuration
@ComponentScan("com.kaixiang.security")
public class CustomSpringSecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider... providers) {
        return new ProviderManager(Arrays.asList(providers));
    }

    @Autowired
    private StandardAuthenticationPermissionEvaluator standardAuthenticationPermissionEvaluator;

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(standardAuthenticationPermissionEvaluator);
        return expressionHandler;
    }
}
