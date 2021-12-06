package com.kaixiang.module.user.converter;

import com.kaixiang.module.user.dto.UpdateProfileDto;
import com.kaixiang.module.user.dto.UserRegisterDto;
import com.kaixiang.module.user.model.UpdateProfileModel;
import com.kaixiang.module.user.model.UserRegisterModel;

public interface UserIdentityProviderConverter<T extends UserRegisterModel, Z extends UserRegisterDto,
    J extends UpdateProfileDto, X extends UpdateProfileModel> {

    T convertToRegisterModel(Z userRegisterDto);

    X convertToUpdateProfileModel(J standardUpdateProfileDto);
}
