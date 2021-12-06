package com.kaixiang.module.user.auth;

import com.kaixiang.module.common.exception.BadRequestException;
import com.kaixiang.module.common.exception.ConflictException;
import com.kaixiang.module.common.exception.RecordNotFoundException;
import com.kaixiang.module.common.exception.UnAuthorizedException;
import com.kaixiang.module.user.converter.UserIdentityProviderConverter;
import com.kaixiang.security.auth.dto.AuthenticatedUserDto;
import com.kaixiang.module.user.dto.UpdateProfileDto;
import com.kaixiang.module.user.dto.UserRegisterDto;
import com.kaixiang.module.user.model.UpdateProfileModel;
import com.kaixiang.module.user.model.UserRegisterModel;

public interface UserIdentityProvider<T extends UserRegisterModel, Z extends UserRegisterDto,
    J extends UpdateProfileDto, X extends UpdateProfileModel> {

    AuthenticatedUserDto authenticate(String email, String password) throws UnAuthorizedException;

    void register(T t) throws BadRequestException, ConflictException;

    UserIdentityProviderConverter<T, Z, J, X> getConverter();

    void updateProfile(X updateProfileDto) throws ConflictException, RecordNotFoundException;
}
