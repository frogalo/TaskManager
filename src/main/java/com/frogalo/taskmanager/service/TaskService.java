package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.entity.User;
import com.frogalo.taskmanager.repository.TaskRepository;
import com.frogalo.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Task> getTasksByProjectId(String userId) {
        return taskRepository.findByProjectId(userId);
    }

    @Transactional
    public Task addUserToTask(String taskId, String userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!task.getUsers().contains(user)) {
            task.addUser(user);
        } else {
            System.err.println("User already assigned to task");
        }

//        if (!user.getTasks().contains(task)) {
//            user.addTask(task);
//        } else {
//            System.err.println("Task already assigned to user");
//        }

        taskRepository.save(task);
//        userRepository.save(user);

        System.out.println(user.getTasks());
        System.out.println(task.getUsers());

        return task;
    }


}
