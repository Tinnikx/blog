package com.kaixiang.module.user.auth;

import com.kaixiang.module.common.exception.BadRequestException;
import com.kaixiang.module.common.exception.UnAuthorizedException;
import com.kaixiang.module.user.constants.Source;
import com.kaixiang.module.user.converter.StandardUserIdentityProviderConverter;
import com.kaixiang.module.user.dto.StandardUserRegisterDto;
import com.kaixiang.module.user.entity.User;
import com.kaixiang.module.user.model.StandardUserRegisterModel;
import com.kaixiang.module.user.service.PermissionService;
import com.kaixiang.module.user.service.UserService;
import com.kaixiang.security.auth.converter.UserIdentityProviderConverter;
import com.kaixiang.security.auth.dto.AuthenticatedUserDto;
import com.kaixiang.security.auth.provider.UserIdentityProvider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import javax.annotation.PostConstruct;

@Service
public class StandardUserIdentityProvider implements UserIdentityProvider<StandardUserRegisterModel, StandardUserRegisterDto> {

    @Autowired
    private IdentityProviderLookupService identityProviderLookupService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StandardUserIdentityProviderConverter standardUserIdentityProviderConverter;

    @PostConstruct
    public void init() {
        identityProviderLookupService.registerIdentityProvider(Source.STANDARD.name(), this);
    }

    @Override public AuthenticatedUserDto authenticate(String email, String password) throws UnAuthorizedException {
        User user = userService.findByEmail(email);
        //TODO
//        if (!user.getActiveStatus()) {
//            throw new UnAuthorizedException("UnAuthorized");
//        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            throw new BadCredentialsException("invalid password");
        }
        String role = permissionService.findPermissions(user.getUuid());
        if (StringUtils.isEmpty(role)) {
            throw new UnAuthorizedException("UnAuthorized");
        }
        return new AuthenticatedUserDto(user.getUuid(),
            user.getEmail(), user.getNickname(), user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(role)), true);
    }

    @Transactional
    @Override public void register(StandardUserRegisterModel registerModel) throws BadRequestException {
        boolean exist = userService.checkEmailExist(registerModel.getEmail()) ||
            userService.checkNicknameExist(registerModel.getNickname());
        if (exist) {
            throw new BadRequestException();
        }

        User user = new User();
        user.setEmail(registerModel.getEmail());
        user.setNickname(registerModel.getNickname());
        user.setPassword(registerModel.getPassword());
        user.setActiveStatus(true);
        user.setUuid(UUID.randomUUID());
        user.setCreated_at(LocalDateTime.now());
        user.setSource(Source.STANDARD);
        userService.create(user);

        permissionService.assignStandardPermissionToUser(user.getUuid());
    }

    @Override public UserIdentityProviderConverter<StandardUserRegisterModel, StandardUserRegisterDto> getConverter() {
        return standardUserIdentityProviderConverter;
    }
}
