package com.task.task.service;

import java.util.List;

import com.task.task.entity.TaskEntity;

public interface TaskService {
    List<TaskEntity> getAllTask();

    TaskEntity getTaskById(Long id);

    TaskEntity saveTask(TaskEntity task);

    void deleteTask(Long id);
}

