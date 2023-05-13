package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.getById(id);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(String id, Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByProjectId(String userId) {
        return taskRepository.findByProjectId(userId);
    }
}
