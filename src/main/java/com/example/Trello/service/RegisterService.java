package com.example.Trello.service;

import com.example.Trello.entity.User;
import com.example.Trello.repository.TaskRepository;
import com.example.Trello.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
public class RegisterService {
    public UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User registerUser(User user) {
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return userRepository.save(user);
    }


}
