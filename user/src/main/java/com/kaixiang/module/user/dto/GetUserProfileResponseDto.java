package com.kaixiang.module.user.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/6
 */
public class GetUserProfileResponseDto {

    private UUID uuid;

    private String email;

    private String nickname;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("email", email)
            .append("nickname", nickname)
            .toString();
    }
}
