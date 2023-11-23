package com.task.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.task.entity.TaskEntity;

@Repository

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}