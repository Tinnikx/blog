package com.kaixiang.module.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserRole {

    @Column
    private String userUuid;

    @Column
    private Integer roleId;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("userUuid", userUuid)
            .append("roleId", roleId)
            .toString();
    }
}
