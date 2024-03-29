package com.kaixiang.module.user.auth;

import com.kaixiang.module.common.exception.BadRequestException;
import com.kaixiang.module.common.exception.ConflictException;
import com.kaixiang.module.common.exception.RecordNotFoundException;
import com.kaixiang.module.common.exception.UnAuthorizedException;
import com.kaixiang.module.user.constants.Source;
import com.kaixiang.module.user.converter.StandardUserIdentityProviderConverter;
import com.kaixiang.module.user.dto.StandardUpdateProfileDto;
import com.kaixiang.module.user.dto.StandardUserRegisterDto;
import com.kaixiang.module.user.repository.entity.User;
import com.kaixiang.module.user.model.StandardUpdateProfileModel;
import com.kaixiang.module.user.model.StandardUserRegisterModel;
import com.kaixiang.module.user.service.PermissionService;
import com.kaixiang.module.user.service.UserService;
import com.kaixiang.module.user.converter.UserIdentityProviderConverter;
import com.kaixiang.security.auth.dto.AuthenticatedUserDto;

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
public class StandardUserIdentityProvider
    implements UserIdentityProvider<StandardUserRegisterModel, StandardUserRegisterDto,
    StandardUpdateProfileDto, StandardUpdateProfileModel> {

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
        if (!user.getActiveStatus()) {
            throw new UnAuthorizedException("Inactive");
        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            throw new BadCredentialsException("invalid password");
        }
        String role = permissionService.findPermissions(user.getUuid());
        if (StringUtils.isEmpty(role)) {
            throw new UnAuthorizedException("UnAuthorized");
        }
        user.setLastLoginAt(LocalDateTime.now());
        userService.update(user);
        return new AuthenticatedUserDto(user.getUuid(),
            user.getEmail(), user.getNickname(), user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(role)), true);
    }

    @Transactional
    @Override public void register(StandardUserRegisterModel registerModel) throws BadRequestException, ConflictException {
//        boolean exist = userService.checkEmailExist(registerModel.getEmail()) ||
//            userService.checkNicknameExist(registerModel.getNickname());
        if (userService.checkIfExist(registerModel.getEmail(), registerModel.getNickname())) {
            throw new ConflictException();
        }

        User user = new User();
        user.setEmail(registerModel.getEmail());
        user.setNickname(registerModel.getNickname());
        user.setPassword(registerModel.getPassword());
        user.setActiveStatus(true);
        user.setUuid(UUID.randomUUID());
        user.setCreatedAt(LocalDateTime.now());
        user.setSource(Source.STANDARD);
        userService.create(user);

        permissionService.assignStandardPermissionToUser(user.getUuid());
    }

    @Override public UserIdentityProviderConverter<StandardUserRegisterModel,
        StandardUserRegisterDto, StandardUpdateProfileDto, StandardUpdateProfileModel> getConverter() {
        return standardUserIdentityProviderConverter;
    }

    @Override public void updateProfile(StandardUpdateProfileModel updateProfileModel)
        throws ConflictException, RecordNotFoundException {
        String email = updateProfileModel.getEmail();
        String nickname = updateProfileModel.getNickname();
        if (userService.checkIfExist(email, nickname)) {
            throw new ConflictException();
        }
        UUID userUuid = updateProfileModel.getUserUuid();
        Boolean userExist = userService.checkUserExistByUuid(userUuid);
        if (!userExist) {
            throw new RecordNotFoundException("user not found");
        }
        User user = new User();
        user.setUuid(userUuid);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(updateProfileModel.getPassword());
        user.setUpdatedAt(LocalDateTime.now());
        userService.update(user);
    }
}
