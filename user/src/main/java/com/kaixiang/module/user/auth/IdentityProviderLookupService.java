package com.kaixiang.module.user.auth;

import com.kaixiang.module.user.constants.Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        return Optional.ofNullable(identityProvider).orElse(identityProviders.get(Source.STANDARD.name()));
    }
}
