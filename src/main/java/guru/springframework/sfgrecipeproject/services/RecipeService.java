package guru.springframework.sfgrecipeproject.services;

import guru.springframework.sfgrecipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> findAll();
    Recipe save(Recipe recipe);
    Recipe findById(Long id);
}
