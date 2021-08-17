package com.kaixiang.module.user.converter;

import com.kaixiang.module.user.dto.StandardUserRegisterDto;
import com.kaixiang.module.user.model.StandardUserRegisterModel;
import com.kaixiang.security.auth.converter.UserIdentityProviderConverter;

import org.springframework.stereotype.Component;

@Component
public class StandardUserIdentityProviderConverter implements UserIdentityProviderConverter<StandardUserRegisterModel, StandardUserRegisterDto> {
    @Override public StandardUserRegisterModel convertToRegisterModel(StandardUserRegisterDto userRegisterDto) {
        StandardUserRegisterModel registerModel = new StandardUserRegisterModel();
        registerModel.setEmail(userRegisterDto.getEmail());
        registerModel.setConfirmedPassword(userRegisterDto.getConfirmedPassword());
        registerModel.setNickname(userRegisterDto.getNickname());
        registerModel.setPassword(userRegisterDto.getPassword());
        registerModel.setSource(userRegisterDto.getSource());
        return registerModel;
    }
}
