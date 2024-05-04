package com.stephen.todolist.controller;

import com.stephen.todolist.pojo.Task;
import com.stephen.todolist.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Add a new task.
     *
     * @param task The task object to be added
     * @return The added task object and HTTP status code
     */
    @ApiOperation(value = "Add a new task")
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    /**
     * Get task details by ID.
     *
     * @param id The task ID
     * @return The task information corresponding to the given ID and HTTP status code
     */
    @ApiOperation(value = "Get task details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Task(), HttpStatus.OK);
        }
    }

    /**
     * Get a list of tasks.
     *
     * @param page Page number
     * @param size Number of tasks per page
     * @return List of tasks and HTTP status code
     */
    @ApiOperation(value = "Get a list of tasks")
    @GetMapping
    public ResponseEntity<Page<Task>> listTasks(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(taskService.listTasks(page, size), HttpStatus.OK);
    }

    /**
     * Delete a task by ID.
     *
     * @param id The task ID
     * @return HTTP status code
     */
    @ApiOperation(value = "Delete a task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Modify a task by ID.
     *
     * @param id           The task ID
     * @param modifiedTask The modified task object
     * @return HTTP status code
     */
    @ApiOperation(value = "Modify a task by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Task> modifyTask(@PathVariable Long id,
                                           @RequestBody Task modifiedTask) {
        taskService.modifyTask(id, modifiedTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
