package com.task.task.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.task.task.entity.TaskEntity;
import com.task.task.exception.TaskNotFoundException;
import com.task.task.repository.TaskRepository;
import com.task.task.service.TaskService;
import com.task.task.service.TaskServiceImpl;

public class TaskServiceTest {

	private TaskService taskService;

	@Mock
	private TaskRepository taskRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		taskService = new TaskServiceImpl(taskRepository);
	}

	@Test
	public void testGetAllTask() {
		List<TaskEntity> task = new ArrayList<>();
		task.add(new TaskEntity(1L, "Saad Zafar", null, null, "saad@domain.com"));
		task.add(new TaskEntity(2L, "Saad Zafar", null, null, "zafar@domain.com"));

		when(taskRepository.findAll()).thenReturn(task);

		List<TaskEntity> result = taskService.getAllTask();

		assertEquals(2, result.size());
	}

	@Test
	public void testGetTaskById() {
		Long taskId = 1L;
		TaskEntity task = new TaskEntity(taskId, "Saad Zafar", null, null, "saad@domain.com");

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

		TaskEntity result = taskService.getTaskById(taskId);

		assertEquals(taskId, result.getId());
		assertEquals("Saad Zafar", result.getName());
		assertEquals("saad@domain.com", result.getEmail());
	}

	@Test
	public void testTaskNotFoundExceptionMessage() {
		String errorMessage = "Task not found";
		TaskNotFoundException exception = new TaskNotFoundException(errorMessage);
		assertEquals(errorMessage, exception.getMessage());
	}

	@Test
	public void testSaveTask() {
		// Prepare test data
		TaskEntity TaskToSave = new TaskEntity(null, "Saad Zafar", null, null, "sheikh@domain.com");
		TaskEntity savedTask = new TaskEntity(1L, "Saad Zafar", null, null, "saad@domain.com");

		// Mock the repository's behavior
		when(taskRepository.save(any(TaskEntity.class))).thenReturn(savedTask);

		// Perform the service call
		TaskEntity result = taskService.saveTask(TaskToSave);

		// Verify the result
		assertNotNull(result.getId());
		assertEquals("Saad Zafar", result.getName());
		assertEquals("saad@domain.com", result.getEmail());
		// You can add more assertions as needed
	}

	@Test
	public void testDeleteTask() {
		Long taskId = 1L;

		taskService.deleteTask(taskId);

		verify(taskRepository, times(1)).deleteById(taskId);
	}
}
