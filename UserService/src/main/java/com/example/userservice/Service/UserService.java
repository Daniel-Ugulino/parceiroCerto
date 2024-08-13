package com.example.userservice.Service;

import com.example.userservice.Domain.Roles;
import com.example.userservice.Domain.Users;
import com.example.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    public Users login(String email, String password) throws Exception{
        Users storedUser  = userRepository.login(email);
        if (storedUser != null && storedUser.isEnabled() && bCryptPasswordEncoder.matches(password, storedUser.getPassword())) {
            return storedUser;
        }else{
            throw new Exception("User not found");
        }
    }

    public void resetPassword(String password, Long id) throws Exception {
        Optional<Users> optionalStoredUser = userRepository.findById(id);
        if (optionalStoredUser.isPresent()) {
            optionalStoredUser.get().setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(optionalStoredUser.get());
        } else {
            throw new Exception("User not found");
        }
    }

    public Users getById(Long id) throws Exception {
        Optional<Users> optionalStoredUser = userRepository.findById(id);
        if (optionalStoredUser.isPresent()) {
            return optionalStoredUser.get();
        } else {
            throw new Exception("User not found");
        }
    }

    public Users save(Users users) {
        users.setRole(Roles.USER);
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        return users;
    }

    public Users update(Users user, Long id) throws Exception {
        Optional<Users> optionalStoredUser = userRepository.findById(id);
        if (optionalStoredUser.isPresent()) {
            Users storedUsers = optionalStoredUser.get();
            user.setId(storedUsers.getId());
            user.setPassword(storedUsers.getPassword());
            user.setRole(storedUsers.getRole());
            user.setCreatedAt(storedUsers.getCreatedAt());
            user.setEnabled(storedUsers.isEnabled());
            userRepository.save(user);
            return user;
        } else {
            throw new Exception("User not found");
        }
    }

    public void delete(Long id) throws Exception{
        Optional<Users> optionalStoredUser = userRepository.findById(id);
        if (optionalStoredUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("User not found");
        }
    }

    public void deactivate(Long id) throws Exception{
        Optional<Users> optionalStoredUser = userRepository.findById(id);
        if (optionalStoredUser.isPresent()) {
            Users storedUsers = optionalStoredUser.get();
            storedUsers.setEnabled(false);
            userRepository.save(storedUsers);
        } else {
            throw new Exception("User not found");
        }
    }

    public void activate(Long id) throws Exception {
        Optional<Users> optionalStoredUser = userRepository.findById(id);
        if (optionalStoredUser.isPresent()) {
            Users storedUsers = optionalStoredUser.get();
            storedUsers.setEnabled(true);
            userRepository.save(storedUsers);
        } else {
            throw new Exception("User not found");
        }
    }

}
