package guru.springframework.sfgrecipeproject.repositories;

import guru.springframework.sfgrecipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Set<Recipe> findAll();
    Recipe save(Recipe recipe);
}
