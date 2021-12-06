package com.kaixiang.security.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Spring Security AuthenticationEntryPoint for our web services.  Since this entry point is only used when an
 * unauthorized user accesses our services, we always respond with an HTTP 401 (Unauthorized).
 *
 * @author pburry
 */
@Component
public class WebServiceAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException authException)
            throws IOException, ServletException {

        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final PrintWriter out = response.getWriter();
        out.print("{\"message\": \"Unauthorized access\"}");
        out.flush();
    }
}
