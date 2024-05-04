package com.example.Trello.service;

import com.example.Trello.entity.User;
import com.example.Trello.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username,String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
