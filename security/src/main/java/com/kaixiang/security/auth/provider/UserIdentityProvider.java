package com.kaixiang.security.auth.provider;

import com.kaixiang.module.common.exception.BadRequestException;
import com.kaixiang.module.common.exception.UnAuthorizedException;
import com.kaixiang.security.auth.converter.UserIdentityProviderConverter;
import com.kaixiang.security.auth.dto.AuthenticatedUserDto;
import com.kaixiang.security.auth.model.UserRegisterDto;
import com.kaixiang.security.auth.model.UserRegisterModel;

public interface UserIdentityProvider<T extends UserRegisterModel, Z extends UserRegisterDto> {

    AuthenticatedUserDto authenticate(String email, String password) throws UnAuthorizedException;

    void register(T t) throws BadRequestException;

    UserIdentityProviderConverter<T, Z> getConverter();
}
