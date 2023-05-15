package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.category.Category;
import com.frogalo.taskmanager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // method to return all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // method to return a category by its ID
    public Category getCategoryById(String id) {
        return categoryRepository.getById(id);
    }

    // method to add a new category
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // method to delete a category by its ID
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    // method to update a category by its ID
    public Category updateCategory(String id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    // method to find categories by name
    public List<Category> findCategoriesByName(String name) {
        return categoryRepository.findByName(name);
    }
}