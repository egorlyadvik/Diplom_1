package praktikum.data;

import praktikum.IngredientType;

public class IngredientData {
    private static final String INGREDIENT_NAME = "Best ingredient";
    private static final float INGREDIENT_PRICE = 12f;
    private static final IngredientType INGREDIENT_TYPE = IngredientType.values()[0];
    private static final int NUMBER_OF_INGREDIENTS = 2;

    public static String getIngredientName() {
        return INGREDIENT_NAME;
    }

    public static float getIngredientPrice() {
        return INGREDIENT_PRICE;
    }

    public static IngredientType getIngredientType() {
        return INGREDIENT_TYPE;
    }

    public static int getNumberOfIngredients() {
        return NUMBER_OF_INGREDIENTS;
    }
}
