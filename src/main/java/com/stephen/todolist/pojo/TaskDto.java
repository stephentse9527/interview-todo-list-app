package com.stephen.todolist.pojo;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "tasks")
public class TaskDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    // 0: pending   1: completed
    private int status;
}
