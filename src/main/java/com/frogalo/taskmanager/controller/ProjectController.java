package com.frogalo.taskmanager.controller;

import com.frogalo.taskmanager.entity.Project;
import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.service.ProjectService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    // metoda zwracająca wszystkie projekty
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Task> addTaskToProject(@PathVariable String projectId, @RequestBody Task task) {
        Task newTask = projectService.addTaskToProject(projectId, task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    // metoda zwracająca projekt o podanym ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    //metoda klasowa
    @GetMapping("/name/{name}")
    public ResponseEntity<Project> getProjectByName(@PathVariable String name) {
        Project project = projectService.getProjectByName(name);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    // metoda dodająca nowy projekt
    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project newProject = projectService.addProject(project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    // metoda usuwająca projekt o podanym ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // metoda aktualizująca projekt o podanym ID
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

}

