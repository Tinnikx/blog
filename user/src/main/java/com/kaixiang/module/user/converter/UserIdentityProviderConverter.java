package com.kaixiang.module.user.converter;

import com.kaixiang.module.user.dto.StandardUpdateProfileDto;
import com.kaixiang.security.auth.dto.UpdateProfileDto;
import com.kaixiang.security.auth.dto.UserRegisterDto;
import com.kaixiang.security.auth.model.UpdateProfileModel;
import com.kaixiang.security.auth.model.UserRegisterModel;

public interface UserIdentityProviderConverter<T extends UserRegisterModel, Z extends UserRegisterDto,
    J extends UpdateProfileDto, X extends UpdateProfileModel> {

    T convertToRegisterModel(Z userRegisterDto);

    X convertToUpdateProfileModel(J standardUpdateProfileDto);
}
