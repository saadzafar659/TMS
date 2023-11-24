package com.task.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.task.entity.TaskEntity;
import com.task.task.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskEntity> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public TaskEntity saveTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
