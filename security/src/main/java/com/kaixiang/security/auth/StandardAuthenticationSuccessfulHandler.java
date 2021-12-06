package com.kaixiang.security.auth;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaixiang.security.utils.AuthUtils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kaixiang Tao
 * @date 2019/12/4
 */
@Component
public class StandardAuthenticationSuccessfulHandler implements AuthenticationSuccessHandler {
    @Override public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 这一步并不是必要的, 但是如果在这一步里需要对 authentication 进行操作并且需要的是操作后的 authentication, 那么这一步就是必要的
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //认证成功之后, 直接返回 response
        try (JsonGenerator jsonGenerator = new JsonFactory().createGenerator(response.getOutputStream(), JsonEncoding.UTF8)){
            response.setHeader("Content-Type", "application/json");
            new ObjectMapper().writeValue(jsonGenerator, Collections.singletonMap("uuid", AuthUtils.getCurrentUser().getUuid()));
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e.getCause());
        }
    }
}
