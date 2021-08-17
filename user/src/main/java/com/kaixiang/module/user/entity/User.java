package com.kaixiang.module.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
public class User {

    @Id
    private Long id;

    @Column
    private String uuid;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean activeStatus;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @Column
    private LocalDateTime last_login_at;

    @Column
    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getLast_login_at() {
        return last_login_at;
    }

    public void setLast_login_at(LocalDateTime last_login_at) {
        this.last_login_at = last_login_at;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("uuid", uuid)
            .append("nickname", nickname)
            .append("email", email)
            .append("password", password)
            .append("activeStatus", activeStatus)
            .append("created_at", created_at)
            .append("updated_at", updated_at)
            .append("last_login_at", last_login_at)
            .append("source", source)
            .toString();
    }
}
