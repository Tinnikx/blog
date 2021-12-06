package com.kaixiang.module.user.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserRegisterModel {

    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
            .append("source", source)
            .toString();
    }
}
