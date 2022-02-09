package guru.springframework.sfgrecipeproject.controllers;

import guru.springframework.sfgrecipeproject.repositories.RecipeRepository;
import guru.springframework.sfgrecipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeRepository);
    }

    @Test
    void index() {
        String viewName = indexController.index(model);
        assertEquals(viewName, "index");
    }
}