package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.entity.User;
import com.frogalo.taskmanager.repository.TaskRepository;
import com.frogalo.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.getById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User updateUser(String id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }


    public List<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }


    public Set<Task> getAllTasksForUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getTasks();
        } else {
            return null;
        }
    }

    public String addTask(String userId, String taskId) {
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
