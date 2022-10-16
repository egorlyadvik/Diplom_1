package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.data.IngredientData;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class IngredientTest {

    public static class NotParameterizedIngredientTest {
        private Ingredient ingredient;
        private IngredientType ingredientType = IngredientData.getIngredientType();
        private String ingredientName = IngredientData.getIngredientName();
        private float ingredientPrice = IngredientData.getIngredientPrice();

        @Before
        public void ingredientInit() {
            ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
        }

        @Test
        public void getNameReturnsIngredientName() {
            assertEquals(ingredientName, ingredient.getName());
        }

        @Test
        public void getPriceReturnsIngredientName() {
            assertEquals(ingredientPrice, ingredient.getPrice(), 0.001f);
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedIngredientTest {
        private Ingredient ingredient;
        private IngredientType expectedIngredientType;
        private String ingredientName = IngredientData.getIngredientName();
        private float ingredientPrice = IngredientData.getIngredientPrice();

        public ParameterizedIngredientTest(IngredientType ingredientType) {
            this.expectedIngredientType = ingredientType;
        }

        @Parameterized.Parameters(name = "Тестовые данные: Тип ингридиента = {0}")
        public static Object[][] getIngredientData() {
            return new Object[][]{
                    {IngredientType.SAUCE},
                    {IngredientType.FILLING}
            };
        }

        @Before
        public void ingredientInit() {
            ingredient = new Ingredient(expectedIngredientType, ingredientName, ingredientPrice);
        }

        @Test
        public void getTypeReturnsIngredientType() {
            IngredientType actualIngredientType = ingredient.getType();

            assertEquals(expectedIngredientType, actualIngredientType);
        }
    }
}