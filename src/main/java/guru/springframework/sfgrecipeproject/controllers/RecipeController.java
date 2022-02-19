package guru.springframework.sfgrecipeproject.controllers;

import guru.springframework.sfgrecipeproject.domain.Difficulty;
import guru.springframework.sfgrecipeproject.domain.Recipe;
import guru.springframework.sfgrecipeproject.services.CategoryService;
import guru.springframework.sfgrecipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
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

    public String updateRecipeCategories()
}
