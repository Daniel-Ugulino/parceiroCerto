package com.example.userservice.Service;

import com.example.userservice.Domain.Roles;
import com.example.userservice.Domain.Users;
import com.example.userservice.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Users users;

    @BeforeEach
    void setUp() {
        this.users = new Users();
        users.setId(1L);
        users.setName("Test");
        users.setPassword("testPassword");
        users.setEmail("test@example.com");
        users.setPhone("123-456-7890");
        users.setRole(Roles.HIRER);
        users.setGender("Homem");
        users.setBirthday(new Date(1985, Calendar.MARCH, 20));
        users.setCreatedAt(new Date());
        users.setEnabled(true);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get User")
    void getUser() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(users));
        Users usersTest = userService.getUser(1L);
        assertNotNull(usersTest);
        assertEquals(users.getId(), usersTest.getId());
        assertEquals(users.getName(), usersTest.getName());
        assertEquals(users.getEmail(), usersTest.getEmail());
    }

    @Test
    @DisplayName("Reset Password")
    void resetPassword() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(users));
        when(userRepository.save(any(Users.class))).thenReturn(users);
        String password = bCryptPasswordEncoder.encode("testPassword");
        userService.resetPassword(password, 1L);
        Users usersTest = userService.getUser(1L);
        assertNotNull(usersTest);
        assertEquals(password, usersTest.getPassword());
    }

    @Test
    @DisplayName("Delete")
    void delete() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(users));
        doNothing().when(userRepository).deleteById(1L);
        userService.delete(1L);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deactivate")
    void deactivate() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(users));
        when(userRepository.save(any(Users.class))).thenReturn(users);
        userService.deactivate(1L);
        Users usersTest = userService.getUser(1L);
        assertNotNull(usersTest);
        assertEquals(false, usersTest.getEnabled());
    }

    @Test
    @DisplayName("Activate")
    void activate() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(users));
        when(userRepository.save(any(Users.class))).thenReturn(users);
        userService.activate(1L);
        Users usersTest = userService.getUser(1L);
        assertNotNull(usersTest);
        assertEquals(true, usersTest.getEnabled());
    }
}