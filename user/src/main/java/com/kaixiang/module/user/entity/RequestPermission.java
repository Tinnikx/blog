package com.kaixiang.module.user.entity;

import com.kaixiang.module.user.upgrade.UpgradeProcessStatus;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/2
 */
@Entity
public class RequestPermission {

    @Id
    private Long id;

    @Column
    private UUID userUuid;

    @Column
    private String reason;

    @Column
    private UpgradeProcessStatus status;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private UUID executorUuid;

    @Column
    private String reject_reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("userUuid", userUuid)
            .append("reason", reason)
            .append("status", status)
            .append("createdAt", createdAt)
            .append("updatedAt", updatedAt)
            .append("executorUuid", executorUuid)
            .append("reject_reason", reject_reason)
            .toString();
    }
}
