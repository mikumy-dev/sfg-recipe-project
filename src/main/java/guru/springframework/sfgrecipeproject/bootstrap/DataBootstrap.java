package guru.springframework.sfgrecipeproject.bootstrap;

import guru.springframework.sfgrecipeproject.domain.Recipe;
import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBootstrap implements CommandLineRunner {
    private final RecipeRepository recipeRepository;

    public DataBootstrap(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDescription("Perfect Guacamole");

        recipeRepository.save(recipe);

        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Spicy Grilled Chicken Tacos");

        recipeRepository.save(recipe2);

    }
}
