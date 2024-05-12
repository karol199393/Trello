package com.example.Trello.unittests;

import com.example.Trello.entity.User;
import com.example.Trello.repository.UserRepository;
import com.example.Trello.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginServiceTest {
    UserRepository userRepository;
    LoginService loginService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        loginService = new LoginService(userRepository);
    }

    @Test
    public void testLoginSuccess() {
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");

        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> loginService.login("testUser", "wrongPassword"));
    }
    @Test
    public void testLoginFailUserNotFound() {
        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> loginService.login("testUser","testPassword"));

    }
    @Test
    public void testLoginNullUser() {
        Mockito.when(userRepository.findByUsername(null)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> loginService.login(null,"testPassword"));
    }
    @Test
    public void testLoginFailPasswordNull() {
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword(null);

        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        assertThrows(RuntimeException.class, () -> loginService.login("testUser",null));
    }
    @Test
    public void testLoginFailPasswordWrong() {
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");

        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        assertThrows(RuntimeException.class, () -> loginService.login("testUser","wrongPassword"));
    }
    @Test
    public void testLoginFailPasswordNullUser() {
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword(null);

        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        assertThrows(RuntimeException.class, () -> loginService.login("testUser","testPassword"));
    }

    @Test
    public void testUsernamenullPasswordOK() {
        User mockUser = new User();
        mockUser.setUsername(null);
        mockUser.setPassword("testPassword");

        Mockito.when(userRepository.findByUsername(null)).thenReturn(mockUser);

        assertThrows(RuntimeException.class, () -> loginService.login(null,"testPassword"));
    }
}