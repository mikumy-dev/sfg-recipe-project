package guru.springframework.sfgrecipeproject.services;

import guru.springframework.sfgrecipeproject.domain.Recipe;
import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Recipe saveWithoutCategoriesAndIngredients(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        if (recipe.getId() == null) {
            return save(recipe);
        }
        Recipe oldRecipe = findById(recipe.getId());
        if (oldRecipe == null) {
            return save(recipe);
        }
        recipe.setCategories(oldRecipe.getCategories());
        recipe.setIngredients(oldRecipe.getIngredients());
        return save(recipe);
    }
}
