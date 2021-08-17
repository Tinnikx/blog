package com.kaixiang.security.configuration;

import com.kaixiang.security.auth.provider.StandardAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

@Configuration
public class CustomSpringConfiguration {

    @Autowired
    private StandardAuthenticationProvider standardAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(standardAuthenticationProvider);
    }
}
