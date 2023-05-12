package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // metoda zwracająca wszystkie projekty
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // metoda zwracająca projekt o podanym ID
    public Project getProjectById(String id) {
        return projectRepository.getById(id);
    }

    // metoda dodająca nowy projekt
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    // metoda usuwająca projekt o podanym ID
    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }

    // metoda aktualizująca projekt o podanym ID
    public Project updateProject(String id, Project project) {
        project.setId(id);
        return projectRepository.save(project);
    }

}

