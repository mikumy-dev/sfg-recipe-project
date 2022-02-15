package guru.springframework.sfgrecipeproject.bootstrap;

import guru.springframework.sfgrecipeproject.domain.*;
import guru.springframework.sfgrecipeproject.repositories.CategoryRepository;
import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import guru.springframework.sfgrecipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import de.svenjacobs.loremipsum.LoremIpsum;

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

        LoremIpsum loremIpsum = new LoremIpsum();

        Recipe recipe = new Recipe();
        recipe.setDescription("Perfect Guacamole");

        recipe.setCookTime(10);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setDirections(loremIpsum.getWords(20));

        recipe.setPrepTime(5);
        recipe.setServings(1);
        recipe.setSource("example-recipes");
        recipe.setUrl("https://www.example-recipes.com");

        Recipe recipeSaved = recipeRepository.save(recipe);

        Set<Category> categories = new HashSet<>();
        categories.add(categoryRepository.findByDescription("American").orElse(null));
        categories.add(categoryRepository.findByDescription("Fast Food").orElse(null));
        Set<Ingredient> ingredients = new HashSet<>();

        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(new BigDecimal(1));
        ingredient.setUom(unitOfMeasureRepository.findByDescription("Cup").orElse(null));
        ingredient.setDescription("milk");
        ingredient.setRecipe(recipeSaved);
        ingredients.add(ingredient);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setAmount(new BigDecimal(1));
        ingredient2.setUom(unitOfMeasureRepository.findByDescription("Ounce").orElse(null));
        ingredient2.setDescription("chocolate");
        ingredient2.setRecipe(recipeSaved);
        ingredients.add(ingredient2);

        Notes notes = new Notes();
        notes.setRecipe(recipeSaved);
        notes.setRecipeNotes(loremIpsum.getWords(100));
        recipeSaved.setIngredients(ingredients);
        recipeSaved.setCategories(categories);
        recipeSaved.setNotes(notes);
        recipeRepository.save(recipeSaved);

        log.debug("add recipe:"+recipeSaved.toString());

        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Spicy Grilled Chicken Tacos");

        Recipe recipe2Saved = recipeRepository.save(recipe2);
        log.debug("add recipe:"+recipe2Saved.toString());
    }
}
