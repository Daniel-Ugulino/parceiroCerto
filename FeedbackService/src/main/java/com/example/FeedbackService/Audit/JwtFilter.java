package com.example.FeedbackService.Audit;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Component
public class JwtFilter extends HttpFilter {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String id = null;
        String jwt = null;

        Cookie cookie = WebUtils.getCookie(request, "access_token");
        if (cookie != null) {
            jwt = cookie.getValue();
        }
        if (jwt != null) {
            id = jwtUtil.extractId(jwt);
            UserContext.setUserId(id);
        }

        chain.doFilter(request, response);
        UserContext.clear();
    }
}
