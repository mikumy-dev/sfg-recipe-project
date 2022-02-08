package guru.springframework.sfgrecipeproject.bootstrap;

import guru.springframework.sfgrecipeproject.domain.Recipe;
import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataBootstrap implements CommandLineRunner {
    private final RecipeRepository recipeRepository;

    public DataBootstrap(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("DataBootstrap#run start");
        Recipe recipe = new Recipe();
        recipe.setDescription("Perfect Guacamole");

        Recipe recipeSaved = recipeRepository.save(recipe);
        log.debug("add recipe:"+recipeSaved.toString());

        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Spicy Grilled Chicken Tacos");

        Recipe recipe2Saved = recipeRepository.save(recipe2);
        log.debug("add recipe:"+recipe2Saved.toString());
        log.debug("DataBootstrap#run end");

    }
}
