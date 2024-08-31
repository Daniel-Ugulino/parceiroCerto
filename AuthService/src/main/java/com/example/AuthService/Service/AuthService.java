package com.example.AuthService.Service;

import com.example.AuthService.Config.TokenProvider;
import com.example.AuthService.Domain.Users;
import com.example.AuthService.Dto.LoginDto;
import com.example.AuthService.Dto.UserDto;
import com.example.AuthService.Repository.LoginRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.Optional;

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

    @Value("${jwt.expiration}")
    public Integer accessTokenExpiration;


    @Value("${jwt.refreshExpiration}")
    public Integer refreshTokenExpiration;

    public UserDto login(LoginDto loginDto, HttpServletResponse response) {
        Authentication auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        Users user = (Users) auth.getPrincipal();

        if(user.isEnabled() && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            this.generateTokens(userDto,response);
            return userDto;
        }else{
            return null;
        }

    }
    public UserDto refreshToken(HttpServletResponse response, HttpServletRequest request) {
        Cookie refreshToken = WebUtils.getCookie(request, "refresh_token");
        String email = tokenProvider.extractEmail(refreshToken.getValue());
        Optional<UserDetails> userDetails = loginRepository.findByEmail(email);
        if(userDetails.isPresent()) {
            Users user = (Users) userDetails.get();
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            this.generateTokens(userDto,response);
            return userDto;
        }
        return null;
    }

    public void generateTokens(UserDto userDto,HttpServletResponse response) {
        String accessToken = tokenProvider.generateAccessToken(userDto.getEmail(), userDto.getId().toString() ,userDto.getRole());
        String refreshToken = tokenProvider.generateRefreshToken(userDto.getEmail(), userDto.getId().toString(), userDto.getRole());

        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setMaxAge(accessTokenExpiration); // 10 minutes
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setMaxAge(refreshTokenExpiration); // 7 days
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);
    }
}
