package com.kaixiang.module.user.dto;

import com.kaixiang.security.auth.dto.UpdateProfileDto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/6
 */
public class StandardUpdateProfileDto extends UpdateProfileDto {

    private String source;

    @NotNull
    private UUID userUuid;

    @Email
    private String email;

    private String nickname;

    //TODO verify the password and confirmed password is the same
    private String password;

    private String confirmedPassword;

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("source", source)
            .append("userUuid", userUuid)
            .append("email", email)
            .append("nickname", nickname)
            .append("password", password)
            .append("confirmedPassword", confirmedPassword)
            .toString();
    }
}
