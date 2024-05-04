package com.stephen.todolist.service;

import com.stephen.todolist.pojo.Task;
import org.springframework.data.domain.Page;

public interface TaskService {
    void addTask(Task task);
    Task getTask(Long id);
    Page<Task> listTasks(int page, int size);
    void deleteTask(Long id);
    void modifyTask(Long id, Task modifiedTask);
}
