package com.kaixiang.module.user.controller;

import com.kaixiang.module.common.exception.BadRequestException;
import com.kaixiang.module.common.exception.ConflictException;
import com.kaixiang.module.common.exception.RecordNotFoundException;
import com.kaixiang.module.user.auth.IdentityProviderLookupService;
import com.kaixiang.module.user.dto.StandardUserRegisterDto;
import com.kaixiang.module.user.dto.UpgradePermissionDto;
import com.kaixiang.module.user.model.RequestPermissionModel;
import com.kaixiang.module.user.service.RequestPermissionService;
import com.kaixiang.module.user.service.UserService;
import com.kaixiang.security.auth.dto.AuthenticatedUserDto;
import com.kaixiang.security.auth.provider.UserIdentityProvider;
import com.kaixiang.security.utils.AuthUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class StandardUserController {

    @Autowired
    private IdentityProviderLookupService identityProviderLookupService;

    @Autowired
    private RequestPermissionService requestPermissionService;

    @Autowired
    private UserService userService;

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

    @DeleteMapping("/del-account")
    @PreAuthorize("hasAnyRole('STANDARD', 'VIP', 'SUPER', 'ADMIN')")
    public void delAccount() throws RecordNotFoundException {
        //TODO send email to verify and store the info to db and delete the user while the email verified
        AuthenticatedUserDto currentUser = AuthUtils.getCurrentUser();
        UUID uuid = currentUser.getUuid();
//        userService.delAccount(uuid);
    }
}
