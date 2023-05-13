package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.entity.User;
import com.frogalo.taskmanager.repository.TaskRepository;
import com.frogalo.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
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

    @Transactional
    public User addTaskToUser(String userId, String taskId) {
        User user = userRepository.findById(userId).orElse(null);
        Task task = taskRepository.findById(taskId).orElse(null);
        if (user == null) {
            System.err.println("User not found");
            return null;
        }
        if (task == null) {
            System.err.println("Task not found");
            return null;
        }
//        task.addUser(user);
        user.addTask(task);
//        taskRepository.save(task);
        userRepository.save(user);
        System.out.println(user.getTasks());
        System.out.println(task.getUsers());

        return user;
    }
}
