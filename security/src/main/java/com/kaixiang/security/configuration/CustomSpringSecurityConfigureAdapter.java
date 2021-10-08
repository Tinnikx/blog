package com.kaixiang.security.configuration;

import com.kaixiang.security.auth.StandardAuthenticationFilter;
import com.kaixiang.security.auth.StandardAuthenticationPermissionEvaluator;
import com.kaixiang.security.auth.WebServiceAuthenticationEntryPoint;
import com.kaixiang.security.auth.WebServiceLogoutSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSpringSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private DefaultWebSecurityExpressionHandler expressionHandler;

    @Autowired
    private StandardAuthenticationPermissionEvaluator standardAuthenticationPermissionEvaluator;

    @Autowired
    private StandardAuthenticationFilter standardAuthenticationFilter;

    @Autowired
    private WebServiceAuthenticationEntryPoint webServiceAuthenticationEntryPoint;

    @Autowired
    private WebServiceLogoutSuccessHandler webServiceLogoutSuccessHandler;

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .addFilterAfter(standardAuthenticationFilter, BasicAuthenticationFilter.class)
            .authorizeRequests().regexMatchers("/*").authenticated()
            .expressionHandler(expressionHandler)
//            .accessDecisionManager(accessDecisionManager)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(webServiceAuthenticationEntryPoint)
            .and()
            .logout().logoutSuccessHandler(webServiceLogoutSuccessHandler);
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(standardAuthenticationPermissionEvaluator);
        return expressionHandler;
    }
}
