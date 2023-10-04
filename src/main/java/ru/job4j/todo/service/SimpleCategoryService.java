package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {

    CategoryRepository categoryRepository;

    @Override
    public Collection<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Collection<Category> findAllById(Collection<Integer> categoriesId) {
        return categoryRepository.findAllById(categoriesId);
    }
}