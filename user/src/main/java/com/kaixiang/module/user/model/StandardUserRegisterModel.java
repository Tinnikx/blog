package com.kaixiang.module.user.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StandardUserRegisterModel extends UserRegisterModel {

    private String email;

    private String nickname;

    private String password;

    private String confirmedPassword;

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
            .append("email", email)
            .append("nickname", nickname)
            .append("password", password)
            .append("confirmedPassword", confirmedPassword)
            .toString();
    }
}
