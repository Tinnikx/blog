package com.kaixiang.module.user.controller;

import com.kaixiang.module.common.exception.BadRequestException;
import com.kaixiang.module.common.exception.ConflictException;
import com.kaixiang.module.user.auth.IdentityProviderLookupService;
import com.kaixiang.module.user.dto.StandardUserRegisterDto;
import com.kaixiang.module.user.dto.UpgradePermissionDto;
import com.kaixiang.module.user.model.RequestPermissionModel;
import com.kaixiang.module.user.service.RequestPermissionService;
import com.kaixiang.security.auth.provider.UserIdentityProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandardUserController {

    @Autowired
    private IdentityProviderLookupService identityProviderLookupService;

    @Autowired
    private RequestPermissionService requestPermissionService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody @Validated StandardUserRegisterDto registerDto) throws BadRequestException, ConflictException {
        UserIdentityProvider identityProvider = identityProviderLookupService.lookup(registerDto.getSource());
        identityProvider.register(identityProvider.getConverter().convertToRegisterModel(registerDto));
    }

    @PutMapping("/upgrade")
    @PreAuthorize("hasAnyRole('STANDARD')")
    @Transactional
    public void upgrade(@RequestBody UpgradePermissionDto upgradePermissionDto) {
        //TODO maybe need message queue
        RequestPermissionModel requestPermissionModel = new RequestPermissionModel();
        requestPermissionModel.setReason(upgradePermissionDto.getReason());
        requestPermissionModel.setUserUuid(upgradePermissionDto.getUuid());
        requestPermissionService.createRequestPermissionRecord(requestPermissionModel);
    }
}
