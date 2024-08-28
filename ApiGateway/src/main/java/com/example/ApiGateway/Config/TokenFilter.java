//package com.example.ApiGateway.Config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.token.Token;
//import org.springframework.security.core.token.TokenService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.WebUtils;
//
//import java.io.IOException;
//
//@Component
//public class TokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @Autowired
//    private CustomUserDetails customUserDetails;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        String jwt = null;
//        String email = null;
//
//        // Get JWT from cookies
//        Cookie cookie = WebUtils.getCookie(request, "access_token");
//        if (cookie != null) {
//            jwt = cookie.getValue();
//        }
//
//        if (jwt != null) {
//            try {
//                email = tokenProvider.extractEmail(jwt);
//                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = this.customUserDetails.loadUserByUsername(email);
//                    if (tokenProvider.isTokenValid(jwt, userDetails)) {
//                        UsernamePasswordAuthenticationToken authToken =
//                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext().setAuthentication(authToken);
//                    }
//                }
//            } catch (Exception e) {
//                    e.printStackTrace();
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
