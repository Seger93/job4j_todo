package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> create(Task task);

    Collection<Task> getAll();

    Optional<Task> getById(int id);

    boolean update(Task tasks);

    boolean updateState(Task tasks);

    boolean deleteById(int id);
}