package com.kaixiang.module.user.model;

import com.kaixiang.security.auth.model.UpdateProfileModel;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/6
 */
public class StandardUpdateProfileModel extends UpdateProfileModel {

    private UUID userUuid;

    private String email;

    private String nickname;

    private String password;

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("userUuid", userUuid)
            .append("email", email)
            .append("nickname", nickname)
            .append("password", password)
            .toString();
    }
}
