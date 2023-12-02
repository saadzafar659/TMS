package com.task.task.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.task.task.model.TaskDto;
import com.task.task.repository.TaskRepository;

import io.cucumber.java.Before;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class HTMLControllerTestIT {

	@Container
	private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7").withDatabaseName("mydb")
			.withUsername("root").withPassword("password").withReuse(true);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TaskRepository taskRepository;

	@Before
	public void setUp() {
		taskRepository.deleteAll();
	}

	@BeforeClass
	public static void beforeAll() {
		mysql.start();
	}

	@AfterClass
	public static void afterAll() {
		mysql.stop();
	}

	@Test
	public void testSaveTask() {
		// Arrange
		TaskDto taskDto = new TaskDto();
		taskDto.setName("Sheikh Saad");
		taskDto.setEmail("sheikh@domain.com");
		taskDto.setNumber("1234567890");

		String url = "http://localhost:" + port + "/saveTask";

		// Act
		ResponseEntity<Void> response = restTemplate.postForEntity(url, taskDto, Void.class);

		// Assert
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertFalse(taskRepository.findAll().isEmpty());
		// Additional assertions or verifications can be added as needed
	}
}