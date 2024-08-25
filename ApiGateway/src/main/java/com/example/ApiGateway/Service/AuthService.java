package com.example.ApiGateway.Service;

import com.example.ApiGateway.Config.TokenProvider;
import com.example.ApiGateway.Domain.Users;
import com.example.ApiGateway.Dto.LoginDto;
import com.example.ApiGateway.Dto.UserDto;
import com.example.ApiGateway.Repository.LoginRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto login(LoginDto loginDto, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Users user = loginRepository.findByEmail(loginDto.getEmail()).orElseThrow();

        if(user.isEnabled() && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            String accessToken = tokenProvider.generateAccessToken(user.getEmail(), user.getRole());
            String refreshToken = tokenProvider.generateRefreshToken(user.getEmail(), user.getRole());

            Cookie accessTokenCookie = new Cookie("access_token", accessToken);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setMaxAge(60 * 10); // 10 minutes
            accessTokenCookie.setPath("/");
            response.addCookie(accessTokenCookie);

            Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
            refreshTokenCookie.setPath("/");
            response.addCookie(refreshTokenCookie);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }else{
            return null;
        }

    }

    public String refreshToken(String refreshToken, HttpServletResponse response) {
        String email = tokenProvider.extractEmail(refreshToken);
        Users user = loginRepository.findByEmail(email).orElseThrow();

        String newAccessToken = tokenProvider.generateAccessToken(user.getEmail(), user.getRole());

        Cookie accessTokenCookie = new Cookie("access_token", newAccessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setMaxAge(60 * 10); // 10 minutes
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        return "Token refreshed";
    }
}
