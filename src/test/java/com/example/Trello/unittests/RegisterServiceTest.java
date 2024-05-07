package com.example.Trello.unittests;

import com.example.Trello.entity.User;
import com.example.Trello.repository.UserRepository;
import com.example.Trello.service.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    private PasswordEncoder encoder;

    private RegisterService registerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        encoder = new BCryptPasswordEncoder();
        registerService = new RegisterService(userRepository, encoder);
    }

    @Test
    void registerUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User registeredUser = registerService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals(user.getUsername(), registeredUser.getUsername());
        assertNotEquals("testPassword", registeredUser.getPassword());

        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUserExisting() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        assertThrows(RuntimeException.class, () -> registerService.registerUser(user));

        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(userRepository, times(0)).save(any(User.class));
    }
}