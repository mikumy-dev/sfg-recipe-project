package guru.springframework.sfgrecipeproject.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RecipeCategories {
    private Long recipeId;
    private Set<Long> categoryIds;
}
