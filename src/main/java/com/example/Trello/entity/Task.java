package com.example.Trello.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Table(name = "tasks")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq_gen")
    @SequenceGenerator(name = "task_seq_gen", sequenceName = "task_seq", allocationSize = 1)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "duedate")
    private String duedate;
    @Column(name = "assignedto")
    private String assignedto;
    @Column(name = "status")
    private String status;

}
