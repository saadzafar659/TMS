package com.task.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.task.task.entity.TaskEntity;
import com.task.task.model.TaskDto;
import com.task.task.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTask() {
        List<TaskEntity> task = taskService.getAllTask();
        List<TaskDto> taskDtos = task.stream()
                .map(task -> new TaskDto(
                		task.getId(),
                		task.getName(),
                		task.getCompany(),
                		task.getNumber(),
                		task.getEmail()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long id) {
        TaskEntity task = taskService.getTaskById(id);
        if (task != null) {
            TaskDto taskDto = new TaskDto(
            		task.getId(),
            		task.getName(),
            		task.getCompany(),
            		task.getNumber(),
            		task.getEmail()
            );
            return new ResponseEntity<>(taskDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity(
                taskDto.getId(),
                taskDto.getName(),
                taskDto.getCompany(),
                taskDto.getNumber(),
                taskDto.getEmail()
        );
        TaskEntity savedTask = taskService.saveTask(taskEntity);
        TaskDto savedTaskDto = new TaskDto(
                savedTask.getId(),
                savedTask.getName(),
                savedTask.getCompany(),
                savedTask.getNumber(),
                savedTask.getEmail()
        );
        return new ResponseEntity<>(savedTaskDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
