package com.task.task.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.task.task.entity.TaskEntity;
import com.task.task.model.TaskDto;
import com.task.task.service.TaskService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class TaskControllerTestIT {

	@Container
	public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.1.0").withUsername("taskmanagement")
			.withPassword("task").withDatabaseName("task");

	@LocalServerPort
	private int port;

	@DynamicPropertySource
	static void databaseProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
		registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.MySQL8Dialect");
		registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mySQLContainer::getUsername);
		registry.add("spring.datasource.password", mySQLContainer::getPassword);
	}

	@Autowired
	private TaskService taskService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetAllTask() {
		// Send a GET request to the controller endpoint
		ResponseEntity<TaskDto[]> response = restTemplate.getForEntity("http://localhost:" + port + "/task",
				TaskDto[].class);

		// Check if the response status code is 200 OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Check if the response body is not empty
		assertNotNull(response.getBody());

	}

	@Test
	public void testGetTaskById() {
		// Create a test TaskEntity and save it to the database
		TaskEntity testEntity = new TaskEntity(/* Initialize with test data */);
		taskService.saveTask(testEntity);

		// Send a GET request to the controller endpoint to retrieve the saved entity
		ResponseEntity<TaskDto> response = restTemplate
				.getForEntity("http://localhost:" + port + "/task/" + testEntity.getId(), TaskDto.class);

		// Check if the response status code is 200 OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}
}
