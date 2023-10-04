package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;

import java.util.Collection;

public interface CategoryService {

    Collection<Category> getAll();

    Collection<Category> findAllById(Collection<Integer> categoriesId);
}