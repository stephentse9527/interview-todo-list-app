package com.stephen.todolist.DAO;

import com.stephen.todolist.pojo.TaskDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskDto, Long> {

}