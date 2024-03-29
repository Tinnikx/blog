package com.kaixiang.module.user.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class StandardUserRegisterDto extends UserRegisterDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    @NotNull
    private String confirmedPassword;

    private String source;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("email", email)
            .append("nickname", nickname)
            .append("password", password)
            .append("confirmedPassword", confirmedPassword)
            .append("source", source)
            .toString();
    }
}
