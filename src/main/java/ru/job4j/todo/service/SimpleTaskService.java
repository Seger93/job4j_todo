package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> create(Task task) {
        return taskRepository.create(task);
    }

    @Override
    public Collection<Task> getAll() {
        return taskRepository.getAll();
    }

    @Override
    public Optional<Task> getById(int id) {
        return taskRepository.getById(id);
    }

    @Override
    public boolean update(Task tasks) {
        return taskRepository.update(tasks);
    }

    @Override
    public boolean updateState(Task tasks) {
        return taskRepository.updateState(tasks);
    }

    @Override
    public boolean deleteById(int id) {
        return taskRepository.deleteById(id);
    }

    @Override
    public Collection<Task> findAllTrue() {
        return taskRepository.findAllTrue();
    }

    @Override
    public Collection<Task> findAllFalse() {
        return taskRepository.findAllFalse();
    }
}