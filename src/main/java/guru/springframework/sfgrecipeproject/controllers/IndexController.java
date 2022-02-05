package guru.springframework.sfgrecipeproject.controllers;

import guru.springframework.sfgrecipeproject.repositories.CategoryRepository;
import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import guru.springframework.sfgrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());

        return "index";
    }
}
