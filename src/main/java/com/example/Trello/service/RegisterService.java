package com.example.Trello.service;

import com.example.Trello.entity.User;
import com.example.Trello.repository.TaskRepository;
import com.example.Trello.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
public class RegisterService {
    public UserRepository userRepository;

    public PasswordEncoder encoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User registerUser(User user) {
        if(user.getUsername() == null || user.getPassword() == null ||user.getEmail() == null) {
            throw new RuntimeException("Username or password cannot be null");
        }
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


}
