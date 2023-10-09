package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.*;

@Repository
@AllArgsConstructor
public class HQLUserRepository implements UserRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
        Optional<User> res = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(user));
            res = Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return crudRepository.optional(
                "from User where email = :fEmail AND password = :fPassword", User.class,
                Map.of("fEmail", email, "fPassword", password)
        );
    }

    @Override
    public Collection<TimeZone> getAllTimeZones() {
        var res = new ArrayList<TimeZone>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            res.add(TimeZone.getTimeZone(timeId));
        }
        return res;
    }
}