package com.kaixiang.module.user.controller;

import com.kaixiang.module.common.exception.BadRequestException;
import com.kaixiang.module.user.dto.StandardUserRegisterDto;
import com.kaixiang.security.auth.provider.UserIdentityProvider;
import com.kaixiang.module.user.auth.IdentityProviderLookupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandardUserController {

    @Autowired
    private IdentityProviderLookupService identityProviderLookupService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody StandardUserRegisterDto registerDto) throws BadRequestException {
        UserIdentityProvider identityProvider = identityProviderLookupService.lookup(registerDto.getSource());
        identityProvider.register(identityProvider.getConverter().convertToRegisterModel(registerDto));
    }

    @PutMapping("/upgrade")
    public void upgrade() {

    }
}
