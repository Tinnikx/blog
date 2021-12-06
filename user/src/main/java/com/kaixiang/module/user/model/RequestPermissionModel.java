package com.kaixiang.module.user.model;

import com.kaixiang.module.user.upgrade.UpgradeProcessStatus;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/2
 */
public class RequestPermissionModel {

    private UUID userUuid;

    private String reason;

    private UpgradeProcessStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID executorUuid;

    private String rejectReason;

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UpgradeProcessStatus getStatus() {
        return status;
    }

    public void setStatus(UpgradeProcessStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getExecutorUuid() {
        return executorUuid;
    }

    public void setExecutorUuid(UUID executorUuid) {
        this.executorUuid = executorUuid;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("userUuid", userUuid)
            .append("reason", reason)
            .append("status", status)
            .append("createdAt", createdAt)
            .append("updatedAt", updatedAt)
            .append("executorUuid", executorUuid)
            .append("rejectReason", rejectReason)
            .toString();
    }
}
