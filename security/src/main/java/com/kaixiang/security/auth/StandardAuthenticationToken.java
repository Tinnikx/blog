package com.kaixiang.security.auth;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author Kaixiang Tao
 * @date 2019/12/5
 */
public class StandardAuthenticationToken extends AbstractAuthenticationToken {

    private String username;

    private String password;

    private String source;

    public StandardAuthenticationToken (String username, String password, String source) {
        super(null);
        this.username = username;
        this.password = password;
        this.source = source;
    }

    @Override public Object getCredentials() {
        return password;
    }

    @Override public Pair<String, String> getPrincipal() {
        return Pair.of(username, source);
    }
}
