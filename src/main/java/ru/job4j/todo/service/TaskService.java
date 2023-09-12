package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {

    Optional<Task> create(Task task);

    Collection<Task> getAll();

    Optional<Task> getById(int id);

    boolean update(Task tasks);

    boolean updateState(Task tasks);

    boolean deleteById(int id);

    Collection<Task> findAllFalse();

    Collection<Task> findAllTrue();
 }