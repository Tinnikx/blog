package com.kaixiang.security.auth.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class AuthenticatedUserDto implements UserDetails, Serializable {

    private UUID uuid;

    private String email;

    private String nickName;

    private String password;

    private List<SimpleGrantedAuthority> authorities;

    private Boolean active;

    public AuthenticatedUserDto(UUID uuid,
                                String email,
                                String nickName,
                                String password,
                                List<SimpleGrantedAuthority> authorities,
                                Boolean active) {
        this.uuid = uuid;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.authorities = authorities;
        this.active = active;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override public String getPassword() {
        return password;
    }

    @Override public String getUsername() {
        return email;
    }

    @Override public boolean isAccountNonExpired() {
        return true;
    }

    @Override public boolean isAccountNonLocked() {
        return true;
    }

    @Override public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override public boolean isEnabled() {
        return active;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("email", email)
            .append("nickName", nickName)
            .append("password", password)
            .append("authorities", authorities)
            .append("active", active)
            .toString();
    }
}
