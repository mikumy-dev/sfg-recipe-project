package guru.springframework.sfgrecipeproject.command;

import guru.springframework.sfgrecipeproject.domain.Ingredient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class RecipeIngredients {
    private Long recipeId;
    private List<Ingredient> ingredients = new ArrayList<>();
}
