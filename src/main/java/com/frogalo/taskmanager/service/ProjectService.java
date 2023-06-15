package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Project;
import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.repository.ProjectRepository;
import com.frogalo.taskmanager.repository.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    // metoda zwracająca wszystkie projekty
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // metoda zwracająca pierwszy projekt o podanej nazwie
    public Project getProjectByName(String name) {
        List<Project> projects = projectRepository.findByName(name);
        if (!projects.isEmpty()) {
            return projects.get(0);
        }
        return null; // lub można rzucić odpowiedni wyjątek
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

    public Task addTaskToProject(String projectId, Task task) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            if (project.getTasks() == null)
                project.setTasks(new ArrayList<Task>());
            project.getTasks().add(task);
            projectRepository.save(project);
            //            task.setProject(project);
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Project not found");
        }
    }

}

