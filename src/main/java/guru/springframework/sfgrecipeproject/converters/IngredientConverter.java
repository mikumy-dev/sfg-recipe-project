package guru.springframework.sfgrecipeproject.converters;

import guru.springframework.sfgrecipeproject.command.IngredientCommand;
import guru.springframework.sfgrecipeproject.domain.Ingredient;
import guru.springframework.sfgrecipeproject.domain.UnitOfMeasure;

public class IngredientConverter {
    public static Ingredient convert(IngredientCommand ingredientCommand) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setAmount(ingredientCommand.getAmount());
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ingredientCommand.getUomId());
        ingredient.setUom(uom);
        return ingredient;
    }

    public static IngredientCommand convert(Ingredient ingredient) {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUomId(ingredient.getUom().getId());
        return ingredientCommand;
    }
}
