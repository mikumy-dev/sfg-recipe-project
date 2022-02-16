package guru.springframework.sfgrecipeproject.controllers;

import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController( RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping("/show/{id}")
    public String showRecipe(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeRepository.findById(id).orElse(null));
        return "recipe/show";
    }

    @RequestMapping("/showForm/{id}")
    public String showRecipeInForm(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeRepository.findById(id).orElse(null));
        return "recipe/form";
    }

    @RequestMapping("/update")
    public String createOrUpdateRecipe() {
        return "redirect:/recipe/show/";
    }
}
