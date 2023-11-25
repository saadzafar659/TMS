package com.task.task.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.task.task.controller.TaskController;
import com.task.task.entity.TaskEntity;
import com.task.task.service.TaskService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void testGetAllTask() throws Exception {
        List<TaskEntity> task = new ArrayList<>();
        task.add(new TaskEntity(1L, "Sheikh Saad", null, null, "sheikh@domain.com"));
        task.add(new TaskEntity(2L, "Sheikh Amin", null, null, "amin@domain.com"));

        Mockito.when(taskService.getAllTask()).thenReturn(task);

        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Sheikh Saad"))
                .andExpect(jsonPath("$[0].email").value("sheikh@domain.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Sheikh Amin"))
                .andExpect(jsonPath("$[1].email").value("amin@domain.com"));

        Mockito.verify(taskService, Mockito.times(1)).getAllTask();
    }

    @Test
    public void testGetTaskById() throws Exception {
        Long taskId = 1L;
        TaskEntity task = new TaskEntity(taskId, "Sheikh Saad", null, null, "sheikh@domain.com");

        Mockito.when(taskService.getTaskById(taskId)).thenReturn(task);

        mockMvc.perform(get("/task/{id}", taskId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(taskId))
                .andExpect(jsonPath("$.name").value("Sheikh Saad"))
                .andExpect(jsonPath("$.email").value("sheikh@domain.com"));

        Mockito.verify(taskService, Mockito.times(1)).getTaskById(taskId);
    }

    @Test
    public void testGetTaskById_TaskNotFound() throws Exception {
        Long contactId = 1L;

        Mockito.when(taskService.getTaskById(contactId)).thenReturn(null);

        mockMvc.perform(get("/task/{id}", contactId))
                .andExpect(status().isNotFound());

        Mockito.verify(taskService, Mockito.times(1)).getTaskById(contactId);
    }

    @Test
    public void testSaveTask() throws Exception {
        TaskEntity task = new TaskEntity(1L, "Sheikh Saad", null, null, "sheikh@domain.com");

        Mockito.when(taskService.saveTask(any(TaskEntity.class))).thenReturn(task);

        mockMvc.perform(post("/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Sheikh Saad\",\"email\":\"sheikh@domain.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sheikh Saad"))
                .andExpect(jsonPath("$.email").value("sheikh@domain.com"));

        Mockito.verify(taskService, Mockito.times(1)).saveTask(any(TaskEntity.class));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Long taskId = 1L;

        mockMvc.perform(delete("/task/{id}", taskId))
                .andExpect(status().isNoContent());

        Mockito.verify(taskService, Mockito.times(1)).deleteTask(taskId);
    }
}
