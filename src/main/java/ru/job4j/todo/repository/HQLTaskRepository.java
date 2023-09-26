package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;


import java.util.*;

@Repository
@AllArgsConstructor
public class HQLTaskRepository implements TaskRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Task> create(Task task) {
        Optional<Task> res = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(task));
            res = Optional.of(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Collection<Task> getAll() {
        return crudRepository.query("from Task", Task.class);
    }

    @Override
    public Optional<Task> getById(int id) {
        return crudRepository.optional("from Task WHERE id = :fId", Task.class,
                Map.of("fId", id));
    }

    @Override
    public boolean update(Task tasks) {
        return crudRepository.runBoolean(session -> {
            session.merge(tasks);
            return true;
        });
    }

    @Override
    public boolean updateState(Task tasks) {
        return crudRepository.runBoolean("UPDATE Task SET done = :done WHERE id = :fId",
                Map.of("done", !tasks.isDone(), "fId", tasks.getId()));
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.runBoolean("DELETE Task WHERE id = :fId",
                Map.of("fId", id));
    }

    @Override
    public Collection<Task> findState(boolean flag) {
        return crudRepository.query("from Task t where done = :fDone order by t.id",
                Task.class,
                Map.of("fDone", flag));
    }
}