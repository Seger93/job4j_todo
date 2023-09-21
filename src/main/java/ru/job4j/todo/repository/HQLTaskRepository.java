package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLTaskRepository implements TaskRepository {

    private final SessionFactory sf;

    @Override
    public Optional<Task> create(Task task) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
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
        return Optional.ofNullable(task);
    }

    @Override
    public Collection<Task> getAll() {
        Session session = this.sf.openSession();
        List<Task> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery("from Task", Task.class).list();
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

    @Override
    public Optional<Task> getById(int id) {
        Session session = this.sf.openSession();
        Optional<Task> result;
        try {
            session.beginTransaction();
            result = Optional.ofNullable(session.get(Task.class, id));
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

    @Override
    public boolean update(Task tasks) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            session.update(tasks);
            session.getTransaction().commit();
            return true;
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
    }

    @Override
    public boolean updateState(Task tasks) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Task SET done = :done WHERE id = :fId")
                    .setParameter("done", !tasks.isDone())
                    .setParameter("fId", tasks.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
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
    }

    @Override
    public boolean deleteById(int id) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Task WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
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
    }

    @Override
    public Collection<Task> findState(boolean flag) {
        Session session = this.sf.openSession();
        Collection<Task> result;
        try {
            session.beginTransaction();
            result = session.createQuery("from Task t where done = :fDone order by t.id", Task.class)
                    .setParameter("fDone", flag)
                    .list();
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