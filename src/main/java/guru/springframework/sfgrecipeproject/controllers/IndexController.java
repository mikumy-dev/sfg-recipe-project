package guru.springframework.sfgrecipeproject.controllers;

import guru.springframework.sfgrecipeproject.repositories.CategoryRepository;
import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import guru.springframework.sfgrecipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeRepository recipeRepository;

    public IndexController( RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping("")
    public String index(Model model) {
        System.out.println(log.isDebugEnabled());
        log.debug("IndexController#index start");
        log.debug("find recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        log.debug("IndexController#index end");
        return "index";
    }
}
