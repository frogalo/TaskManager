package com.frogalo.taskmanager.repository;

import com.frogalo.taskmanager.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    // znajdź projekt po ID
    public Project getById(String id);

    // znajdź projekty po nazwie
    public List<Project> findByName(String title);

    // znajdź projekty po dacie rozpoczęcia
    public List<Project> findByStartDate(Date startDate);

    // znajdź projekty po dacie zakończenia
    public List<Project> findByEndDate(Date endDate);

    // zapisz projekt
    public Project save(Project project);

    // usuń projekt po ID
    public void deleteById(String id);


}
