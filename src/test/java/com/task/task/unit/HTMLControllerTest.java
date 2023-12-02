package com.task.task.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.task.controller.HTMLController;
import com.task.task.model.TaskDto;
import com.task.task.repository.TaskRepository;

@SpringBootTest
public class HTMLControllerTest {

	@Mock
	private TaskRepository taskRepository;

	private HTMLController htmlController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		htmlController = new HTMLController(taskRepository);
	}

	@Test
	public void testSaveContact() {
		TaskDto taskDto = new TaskDto();
		taskDto.setId(1L);
		taskDto.setName("Sheikh Saad");
		taskDto.setCompany("SDTechnologist");
		taskDto.setNumber("1234567890");
		taskDto.setEmail("sheikh@domain.com");

		htmlController.saveTask(taskDto);

		Mockito.verify(taskRepository).save(Mockito.any()); // Update this line to accept any TaskDto object
	}
}
