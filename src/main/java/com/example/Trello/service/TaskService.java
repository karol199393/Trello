package com.example.Trello.service;

import com.example.Trello.entity.Task;
import com.example.Trello.entity.User;
import com.example.Trello.repository.TaskRepository;
import com.example.Trello.repository.UserRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    public TaskRepository taskRepository;
    public UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        return taskRepository.save(task); }

}
