package com.taskapp.service.impl;

import com.taskapp.dto.UserDto;
import com.taskapp.model.Task;
import com.taskapp.repository.TaskRepository;
import com.taskapp.service.TaskService;
import com.taskapp.util.TaskUtil;
import com.taskapp.util.UtilTest;
import com.taskapp.util.impl.TaskUtilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;
    @Mock
    RestTemplate restTemplate;
    @Mock
    TaskUtil taskUtil;
    @InjectMocks
    TaskServiceImpl taskServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() {
        when(taskRepository.findAll()).thenReturn(UtilTest.TASK_LIST);
        List<Task> tasks = taskServiceImpl.findAll();
        verify(taskRepository).findAll();
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertTrue(tasks.size()==3);
    }

    @Test
    void findByUuidTest() {
        String uuid = UtilTest.TASK.getUuid();
        when(taskRepository.findByUuid(uuid)).thenReturn(UtilTest.TASK);
        Task task = taskServiceImpl.findByUuid(uuid);
        verify(taskRepository).findByUuid(uuid);
        assertNotNull(task);
        assertEquals(1L, task.getId());
        assertEquals("barrer", task.getResume());
        assertEquals(1, task.getIdType());
    }

    @Test
    void updateTaskTest() {
        String uuid = UtilTest.TASK.getUuid();
        when(taskRepository.save(UtilTest.TASK)).thenReturn(UtilTest.TASK);
        when(taskRepository.findByUuid(uuid)).thenReturn(UtilTest.TASK);
        when(taskUtil.getTimeOfTask(anyInt())).thenReturn(LocalTime.of(2,0));
        Task task = taskServiceImpl.updateTask(uuid, UtilTest.TASK);
        assertNotNull(uuid);
        assertNotNull(task);
        assertTrue(task instanceof Task);
        assertEquals(uuid, task.getUuid());
    }

    @Test
    void assignTaskTest() {
        String uuidTask = UtilTest.TASK.getUuid();
        String uuidUser = UtilTest.USER_DTO.getUuid();
        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("uuid", uuidUser);
        when(taskRepository.findByUuid(uuidTask)).thenReturn(UtilTest.TASK);
        when(restTemplate.getForObject("http://localhost:8090/api/user/{uuid}", UserDto.class, pathVariable)).thenReturn(UtilTest.USER_DTO);
        when(taskRepository.save(UtilTest.TASK)).thenReturn(UtilTest.TASK);
        //invocando metodo
        Task task = taskServiceImpl.assignTask(uuidTask, uuidUser);
        //realizando pruebas
        verify(taskRepository).findByUuid(uuidTask);
        verify(restTemplate).getForObject("http://localhost:8090/api/user/{uuid}", UserDto.class, pathVariable);
        assertNotNull(task);
        assertNotNull(task.getId());
        assertEquals(1L, task.getId());
        assertNotNull(task.getResume());
        assertEquals("barrer", task.getResume());
        assertNotNull(task.getIdType());
        assertEquals(1, task.getIdType());
        assertNotNull(task.getCreateAt());
        assertNull(task.getUpdateAt());
    }

    @Test
    void saveTaskTest() {
        when(taskRepository.save(any(Task.class))).thenReturn(UtilTest.TASK);
        when(taskUtil.getTimeOfTask(anyInt())).thenReturn(LocalTime.of(2,0));
        Task task = taskServiceImpl.saveTask(UtilTest.TASK);
        verify(taskRepository).save(UtilTest.TASK);
        verify(taskUtil).getTimeOfTask(1);
        assertInstanceOf(Task.class, task);
        assertNotNull(task);
    }
}