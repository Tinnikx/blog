package com.kaixiang.security.auth.service;

import com.kaixiang.module.common.exception.RecordNotFoundException;
import com.kaixiang.security.auth.provider.UserIdentityProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IdentityProviderLookupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityProviderLookupService.class);

    private Map<String, UserIdentityProvider> identityProviders = new HashMap<>();

    public void registerIdentityProvider(String source, UserIdentityProvider identityProvider) {
        if (identityProvider == null) {
            throw new IllegalArgumentException(String.format("identity provider is null for source %s", source));
        }
        identityProviders.put(source, identityProvider);
    }

    public UserIdentityProvider lookup(String source) {
        UserIdentityProvider identityProvider = identityProviders.get(source);
        if (identityProvider == null) {
            LOGGER.warn("identity provider not found for source {}", source);
        }
        return identityProvider;
    }
}
