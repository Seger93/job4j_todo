package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.HQLUserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.TimeZone;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {

    private final HQLUserRepository hqlUserRepository;

    @Override
    public Optional<User> save(User user) {
        return hqlUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
       return hqlUserRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Collection<TimeZone> getAllTimeZones() {
        return hqlUserRepository.getAllTimeZones();
    }
}