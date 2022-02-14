package guru.springframework.sfgrecipeproject.bootstrap;

import guru.springframework.sfgrecipeproject.domain.Category;
import guru.springframework.sfgrecipeproject.domain.Difficulty;
import guru.springframework.sfgrecipeproject.domain.Ingredient;
import guru.springframework.sfgrecipeproject.domain.Recipe;
import guru.springframework.sfgrecipeproject.repositories.CategoryRepository;
import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import guru.springframework.sfgrecipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class DataBootstrap implements CommandLineRunner {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("DataBootstrap#run start");
        addRecipeDetails();

        log.debug("DataBootstrap#run end");

    }

    public void addRecipeDetails() {

        Recipe recipe = new Recipe();
        recipe.setDescription("Perfect Guacamole");

        recipe.setCookTime(10);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setDirections("Directions");

        recipe.setPrepTime(5);
        recipe.setServings(1);
        recipe.setSource("source");

        Recipe recipeSaved = recipeRepository.save(recipe);

        Set<Category> categories = new HashSet<>();
        categories.add(categoryRepository.findByDescription("American").orElse(null));
        categories.add(categoryRepository.findByDescription("Fast Food").orElse(null));
        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(new BigDecimal(1));
        ingredient.setUom(unitOfMeasureRepository.findByDescription("Cup").orElse(null));
        ingredient.setDescription("milk");

        ingredients.add(ingredient);
        recipeSaved.setIngredients(ingredients);
        recipeSaved.setCategories(categories);
        recipeRepository.save(recipeSaved);

        log.debug("add recipe:"+recipeSaved.toString());

        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Spicy Grilled Chicken Tacos");

        Recipe recipe2Saved = recipeRepository.save(recipe2);
        log.debug("add recipe:"+recipe2Saved.toString());
    }
}
