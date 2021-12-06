package com.kaixiang.module.user.auth;

import com.kaixiang.security.auth.StandardAuthenticationToken;
import com.kaixiang.security.auth.dto.AuthenticatedUserDto;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author Kaixiang Tao
 * @date 2019/11/20
 */
@Lazy
@Component
public class StandardAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IdentityProviderLookupService identityProviderLookupService;

    @Override public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Pair<String, String> usernameAndSource = (Pair<String, String>) authentication.getPrincipal();
        UserIdentityProvider identityProvider = identityProviderLookupService.lookup(usernameAndSource.getRight());

        AuthenticatedUserDto authenticatedUserDto;
        try {
            authenticatedUserDto = identityProvider.authenticate(usernameAndSource.getLeft(), authentication.getCredentials().toString());
        }catch (AuthenticationException ex) {
            ex.printStackTrace();
            throw ex;
        }catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationServiceException("Authenticate Failed");
        }
        return new UsernamePasswordAuthenticationToken(authenticatedUserDto, authentication.getCredentials(), authenticatedUserDto.getAuthorities());
    }

    @Override public boolean supports(Class<?> authentication) {
        return authentication.equals(StandardAuthenticationToken.class);
    }
}
