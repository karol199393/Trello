package com.example.Trello.controller;

import com.example.Trello.entity.Task;
import com.example.Trello.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
     private final TaskService taskService;

     public TaskController(TaskService taskService) {
         this.taskService = taskService;
     }

     @GetMapping("/tasks")
     public ResponseEntity<List<Task>> getAllTrainings() {
         List<Task> trainings = taskService.getAllTasks();
         return new ResponseEntity<>(trainings, HttpStatus.OK);
     }

     @GetMapping("/tasks/{id}")
     public Task getTaskById(@PathVariable Long id) {
         return taskService.getTaskById(id); }

}
