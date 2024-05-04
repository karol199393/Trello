package com.example.Trello.service;

import com.example.Trello.entity.Task;
import com.example.Trello.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    public TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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




}
