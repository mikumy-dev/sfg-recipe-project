package guru.springframework.sfgrecipeproject.controllers;

import guru.springframework.sfgrecipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("")
    public String index(Model model) {
        System.out.println(log.isDebugEnabled());
        log.debug("IndexController#index start");
        log.debug("find recipes");
        model.addAttribute("recipes", recipeService.findAll());
        log.debug("IndexController#index end");
        return "index";
    }
}
