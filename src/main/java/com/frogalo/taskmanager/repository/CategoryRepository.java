package com.frogalo.taskmanager.repository;

import com.frogalo.taskmanager.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    Category getById(String id);

    // Additional method
    List<Category> findByName(String name);
}
