package com.kaixiang.security.auth.converter;

import com.kaixiang.security.auth.model.UserRegisterDto;
import com.kaixiang.security.auth.model.UserRegisterModel;

public interface UserIdentityProviderConverter<T extends UserRegisterModel, Z extends UserRegisterDto> {

    T convertToRegisterModel(Z userRegisterDto);
}
