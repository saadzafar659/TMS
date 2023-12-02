package com.task.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.task.task.entity.TaskEntity;
import com.task.task.model.TaskDto;
import com.task.task.repository.TaskRepository;

@Controller
public class HTMLController {
    private final TaskRepository taskRepository;

    @Autowired
    public HTMLController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute TaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity(
                taskDto.getId(),
                taskDto.getName(),
                taskDto.getCompany(),
                taskDto.getNumber(),
                taskDto.getEmail()
        );
        taskRepository.save(taskEntity);
        return "redirect:/";
    }
}
