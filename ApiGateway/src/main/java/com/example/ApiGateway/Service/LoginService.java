package com.example.ApiGateway.Service;

import com.example.ApiGateway.Domain.Users;
import com.example.ApiGateway.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    LoginRepository loginRepository;

    public Users login(String email, String password) throws Exception{
        Users storedUser  = loginRepository.login(email);
        if (storedUser != null && storedUser.isEnabled() && bCryptPasswordEncoder.matches(password, storedUser.getPassword())) {
            return storedUser;
        }else{
            throw new Exception("User not found");
        }
    }
}
