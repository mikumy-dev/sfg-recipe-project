package guru.springframework.sfgrecipeproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgrecipeproject.command.IngredientCommand;
import guru.springframework.sfgrecipeproject.command.RecipeCategories;
import guru.springframework.sfgrecipeproject.command.RecipeIngredients;
import guru.springframework.sfgrecipeproject.converters.IngredientConverter;
import guru.springframework.sfgrecipeproject.domain.*;
import guru.springframework.sfgrecipeproject.services.CategoryService;
import guru.springframework.sfgrecipeproject.services.RecipeService;
import guru.springframework.sfgrecipeproject.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;
    private final UnitOfMeasureService unitOfMeasureService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService,
                            UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping("/show/{id}")
    public String showRecipe(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @RequestMapping("/showForm/{id}")
    public String showRecipeInForm(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.findById(id));
        model.addAttribute("difficulties", Difficulty.values());
        model.addAttribute("categories", categoryService.findAll());
        return "recipe/form";
    }

    @PostMapping("/update")
    public String createOrUpdateRecipe(@ModelAttribute Recipe recipe) {
        Recipe recipeSaved = recipeService.saveWithoutCategoriesAndIngredients(recipe);
        return "redirect:/recipe/show/" + recipeSaved.getId();
    }

    @RequestMapping("/editCategories/{id}")
    public String toUpdateCategoryPage(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        RecipeCategories recipeCategories = new RecipeCategories();
        recipeCategories.setRecipeId(recipe.getId());
        Set<Long> categoryIds = new HashSet<>();
        recipe.getCategories().forEach(category -> categoryIds.add(category.getId()));
        recipeCategories.setCategoryIds(categoryIds);
        model.addAttribute("recipeCategories", recipeCategories);
        model.addAttribute("categories", categoryService.findAll());
        return "recipe/editCategories";
    }

    @PostMapping("/updateRecipeCategories")
    public String updateRecipeCategories(@ModelAttribute RecipeCategories recipeCategories) {
        Recipe recipe = recipeService.findById(recipeCategories.getRecipeId());
        Set<Category> categories = new HashSet<>();
        recipeCategories.getCategoryIds().forEach(id -> categories.add(categoryService.findById(id)));
        recipe.setCategories(categories);
        Recipe recipeSaved = recipeService.save(recipe);
        return "redirect:/recipe/show/" + recipeSaved.getId();
    }

    @RequestMapping("/editIngredients/{id}")
    public String editIngredients(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        RecipeIngredients recipeIngredients = new RecipeIngredients();
        recipeIngredients.setRecipeId(recipe.getId());
        List<IngredientCommand> ingredientCommandList = new ArrayList<>();
        recipe.getIngredients().forEach(ingredient -> {
            ingredientCommandList.add(IngredientConverter.convert(ingredient));
        });
        recipeIngredients.setIngredientCommandList(ingredientCommandList);
        model.addAttribute("recipeIngredients", recipeIngredients);

        List<UnitOfMeasure> unitOfMeasureList = unitOfMeasureService.findAll();
        model.addAttribute("unitOfMeasureList", unitOfMeasureList);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(recipeIngredients);
            log.debug(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "recipe/editIngredients";
    }

    @ResponseBody
    @RequestMapping("/updateRecipeIngredients")
    public String updateRecipeIngredients(@RequestBody RecipeIngredients recipeIngredients) {
        Recipe recipe = recipeService.findById(recipeIngredients.getRecipeId());
        List<IngredientCommand> ingredientCommandList = recipeIngredients.getIngredientCommandList();
        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientCommandList.forEach(ingredientCommand -> {
            Ingredient ingredientToSave = IngredientConverter.convert(ingredientCommand);
            ingredientToSave.setUom(unitOfMeasureService.findById(ingredientCommand.getUomId()));
            ingredientToSave.setRecipe(recipe);
            ingredientSet.add(ingredientToSave);
        });

        recipe.setIngredients(ingredientSet);
        Recipe recipeSaved = recipeService.save(recipe);
        // return an empty json
        return "{}";
    }
}
