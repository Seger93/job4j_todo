package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;


import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLUserRepository implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(HQLUserRepository.class.getName());

    private final SessionFactory sf;

    @Override
    public Optional<User> save(User user) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Session session = this.sf.openSession();
        Optional<User> result;
        try {
            session.beginTransaction();
           result = session.createQuery("from User where email = :fEmail AND password = :fPassword", User.class)
                    .setParameter("fEmail", email)
                    .setParameter("fPassword", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
}