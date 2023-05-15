package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.entity.User;
import com.frogalo.taskmanager.repository.TaskRepository;
import com.frogalo.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
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

    public Set<Task> getTasksByProjectId(String userId) {
        return taskRepository.findByProjectId(userId);
    }

    public String addUser(String userId, String taskId) {
        User user = userRepository.findById(userId).orElse(null);
        Task task = taskRepository.findById(taskId).orElse(null);
        if (user == null) {
            System.err.println("User " + userId + " does not exist");
            return "User " + userId + " does not exist";
        }
        if (task == null) {
            System.err.println("Task with id: " + taskId + " does not exist");
            return "Task with id: " + taskId + " does not exist";
        }
        Set<Task> tasks = taskRepository.findByUsersId(userId);
        Set<User> users = userRepository.findByTasksId(taskId);
        System.out.println(task);
        System.out.println(tasks);
        System.out.println(user);
        System.out.println(users);

        if (tasks.stream().anyMatch(t -> t.getId().equals(taskId)) || users.stream().anyMatch(u -> u.getId().equals(userId))) {
            return "This user already has this task";
        }
        user.getTasks().add(task);
        userRepository.save(user);

        task.getUsers().add(user);
        taskRepository.save(task);
        return "Task added successfully";
    }
}
