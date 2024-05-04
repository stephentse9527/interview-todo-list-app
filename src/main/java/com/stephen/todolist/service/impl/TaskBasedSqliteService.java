package com.stephen.todolist.service.impl;

import com.stephen.todolist.DAO.TaskRepository;
import com.stephen.todolist.pojo.Task;
import com.stephen.todolist.pojo.TaskDto;
import com.stephen.todolist.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskBasedSqliteService implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTask(Task task) {
        TaskDto taskDTO = new TaskDto();
        BeanUtils.copyProperties(task, taskDTO);
        taskRepository.save(taskDTO);
    }

    @Override
    public Task getTask(Long id) {
        TaskDto taskDto = taskRepository.findById(id).orElse(null);
        if (taskDto != null) {
            Task task = new Task();
            BeanUtils.copyProperties(taskDto, task);
            return task;
        }
        return null;
    }

    @Override
    public Page<Task> listTasks(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> taskDtos = taskRepository.findAll(pageable);
        List<Task> tasks = taskDtos.map(dto -> {
            Task task = new Task();
            BeanUtils.copyProperties(dto, task);
            return task;
        }).stream().collect(Collectors.toList());
        return new PageImpl<>(tasks, taskDtos.getPageable(), taskDtos.getTotalElements());
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void modifyTask(Long id, Task modifiedTas) {
        if (id != null) {
            TaskDto task = taskRepository.findById(id).orElse(null);
            if (task != null) {
                BeanUtils.copyProperties(modifiedTas, task);
                task.setId(id);
                taskRepository.save(task);
            }
        }
    }
}
