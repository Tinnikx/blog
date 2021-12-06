package com.kaixiang.module.user.converter;

import com.kaixiang.module.user.dto.StandardUserRegisterDto;
import com.kaixiang.module.user.dto.StandardUpdateProfileDto;
import com.kaixiang.module.user.model.StandardUpdateProfileModel;
import com.kaixiang.module.user.model.StandardUserRegisterModel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class StandardUserIdentityProviderConverter
    implements UserIdentityProviderConverter<StandardUserRegisterModel,
    StandardUserRegisterDto, StandardUpdateProfileDto, StandardUpdateProfileModel> {
    @Override public StandardUserRegisterModel convertToRegisterModel(StandardUserRegisterDto userRegisterDto) {
        StandardUserRegisterModel registerModel = new StandardUserRegisterModel();
        registerModel.setEmail(StringUtils.trim(userRegisterDto.getEmail()));
        registerModel.setConfirmedPassword(userRegisterDto.getConfirmedPassword());
        registerModel.setNickname(userRegisterDto.getNickname());
        registerModel.setPassword(userRegisterDto.getPassword());
        registerModel.setSource(userRegisterDto.getSource());
        return registerModel;
    }

    @Override public StandardUpdateProfileModel convertToUpdateProfileModel(StandardUpdateProfileDto standardUpdateProfileDto) {
        StandardUpdateProfileModel model = new StandardUpdateProfileModel();
        model.setUserUuid(standardUpdateProfileDto.getUserUuid());
        model.setEmail(standardUpdateProfileDto.getEmail());
        model.setNickname(standardUpdateProfileDto.getNickname());
        model.setPassword(standardUpdateProfileDto.getPassword());
        return model;
    }
}
