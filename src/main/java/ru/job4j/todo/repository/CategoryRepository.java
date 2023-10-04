package ru.job4j.todo.repository;

import ru.job4j.todo.model.Category;

import java.util.Collection;

public interface CategoryRepository {

 Collection<Category> getAll();

 Collection<Category> findAllById(Collection<Integer> categoriesId);

}