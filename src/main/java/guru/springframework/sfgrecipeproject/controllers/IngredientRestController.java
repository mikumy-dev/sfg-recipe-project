package guru.springframework.sfgrecipeproject.controllers;

import guru.springframework.sfgrecipeproject.command.IngredientCommand;
import guru.springframework.sfgrecipeproject.command.RecipeIngredients;
import guru.springframework.sfgrecipeproject.converters.IngredientConverter;
import guru.springframework.sfgrecipeproject.domain.Ingredient;
import guru.springframework.sfgrecipeproject.domain.Recipe;
import guru.springframework.sfgrecipeproject.services.CategoryService;
import guru.springframework.sfgrecipeproject.services.RecipeService;
import guru.springframework.sfgrecipeproject.services.UnitOfMeasureService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientRestController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientRestController(RecipeService recipeService, CategoryService categoryService,
                            UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping("/getRecipeIngredients/{recipeId}")
    public RecipeIngredients getRecipeIngredients(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.findById(recipeId);
        RecipeIngredients recipeIngredients = new RecipeIngredients();
        recipeIngredients.setRecipeId(recipe.getId());
        List<IngredientCommand> ingredientCommandList = new ArrayList<>();
        recipe.getIngredients().forEach(ingredient -> {
            ingredientCommandList.add(IngredientConverter.convert(ingredient));
        });
        recipeIngredients.setIngredientCommandList(ingredientCommandList);
        return recipeIngredients;
    }
}
