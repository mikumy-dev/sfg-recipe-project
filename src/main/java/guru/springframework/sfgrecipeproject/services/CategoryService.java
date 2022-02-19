package guru.springframework.sfgrecipeproject.services;

import guru.springframework.sfgrecipeproject.domain.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> findAll();
}
