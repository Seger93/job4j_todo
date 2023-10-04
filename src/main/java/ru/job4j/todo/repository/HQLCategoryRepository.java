package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.Collection;
import java.util.Map;

@Repository
@AllArgsConstructor
public class HQLCategoryRepository implements CategoryRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Category> getAll() {
        return crudRepository.query("from Category", Category.class);
    }

    @Override
    public Collection<Category> findAllById(Collection<Integer> categoriesId) {
        return crudRepository.query("FROM Category c WHERE c.id IN :fCategoryList",
                Category.class, Map.of("fCategoryList", categoriesId));
    }
}