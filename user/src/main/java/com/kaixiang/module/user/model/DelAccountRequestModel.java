package com.kaixiang.module.user.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/5
 */
public class DelAccountRequestModel {

    private UUID uuid;

    private LocalDateTime createdAt;

    private Duration effectiveTime;

    private String email;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Duration getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Duration effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("createdAt", createdAt)
            .append("effectiveTime", effectiveTime)
            .append("email", email)
            .toString();
    }
}
