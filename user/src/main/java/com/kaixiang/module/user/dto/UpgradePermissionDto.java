package com.kaixiang.module.user.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/2
 */
public class UpgradePermissionDto {

    private UUID uuid;

    private String reason;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("reason", reason)
            .toString();
    }
}
