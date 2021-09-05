package io.murad.modern.ecommerce.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        final String EXPIRED = (String) httpServletRequest.getAttribute("expired");
        System.out.println(EXPIRED);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, Objects.requireNonNullElse(EXPIRED, "Invalid Login details"));
    }
}
