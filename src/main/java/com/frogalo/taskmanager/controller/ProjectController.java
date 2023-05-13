package com.frogalo.taskmanager.controller;

import com.frogalo.taskmanager.entity.Project;
import com.frogalo.taskmanager.service.ProjectService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

//    @PostConstruct
//    public void init() {
//        Project project = new Project("Project 1", "Description 1", new Date(), new Date());
//        projectService.addProject(project);
//    }

//    @GetMapping
//    List<Project> getProjects(Model model) {
//        List<Project> projects = projectService.getAllProjects();
//        model.addAttribute("projects", projects);
//        return projects;
//    }

    // metoda zwracająca wszystkie projekty
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // metoda zwracająca projekt o podanym ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
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

