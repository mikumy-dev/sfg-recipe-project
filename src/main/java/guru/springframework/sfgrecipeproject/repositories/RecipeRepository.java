package guru.springframework.sfgrecipeproject.repositories;

import guru.springframework.sfgrecipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
