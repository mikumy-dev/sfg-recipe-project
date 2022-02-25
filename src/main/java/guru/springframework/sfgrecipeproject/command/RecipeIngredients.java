package guru.springframework.sfgrecipeproject.command;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeIngredients {
    private Long recipeId;
    private List<IngredientCommand> ingredientCommandList = new ArrayList<>();
}
