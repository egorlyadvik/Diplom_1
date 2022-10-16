package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.data.BunData;
import praktikum.data.IngredientData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;
    private String bunName = BunData.getBunName();
    private float bunPrice = BunData.getBunPrice();
    private String ingredientName = IngredientData.getIngredientName();
    private float ingredientPrice = IngredientData.getIngredientPrice();
    private IngredientType ingredientType = IngredientData.getIngredientType();
    private int numberOfIngredients = IngredientData.getNumberOfIngredients();

    @Mock
    Bun bunMock;

    @Mock
    Ingredient ingredientMock;

    @Before
    public void setUp() {
        burger = new Burger();
        Mockito.when(bunMock.getName()).thenReturn(bunName);
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredientMock.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientMock.getPrice()).thenReturn(ingredientPrice);
        Mockito.when(ingredientMock.getType()).thenReturn(ingredientType);
    }

    @Test
    public void setBunsSetsBunBurger() {
        burger.setBuns(bunMock);

        Bun expectedBun = bunMock;
        Bun actualBun = burger.bun;

        assertEquals(expectedBun, actualBun);
    }

    @Test
    public void addIngredientAddsIngredientToListOfIngredients() {
        List<Ingredient> expectedIngredients = new ArrayList<>();
        List<Ingredient> actualIngredients = burger.ingredients;

        expectedIngredients.add(ingredientMock);
        burger.addIngredient(ingredientMock);

        assertEquals(expectedIngredients, actualIngredients);
    }

    @Test
    public void removeIngredientRemovesIngredientFromListOfIngredients() {
        List<Ingredient> expectedIngredients = new ArrayList<>();
        List<Ingredient> actualIngredients = burger.ingredients;

        for (int i = 0; i < numberOfIngredients; i++) {
            expectedIngredients.add(ingredientMock);
            burger.addIngredient(ingredientMock);
        }

        int indexOfIngredient = (int) (Math.random() * numberOfIngredients);

        expectedIngredients.remove(indexOfIngredient);
        burger.removeIngredient(indexOfIngredient);

        assertEquals(expectedIngredients, actualIngredients);
    }

    @Test
    public void moveIngredientChangesPlacesOfIngredients() {
        List<Ingredient> expectedIngredients = new ArrayList<>();
        List<Ingredient> actualIngredients = burger.ingredients;
        Ingredient ingredient;

        for (int i = 0; i < numberOfIngredients; i++) {
            ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
            expectedIngredients.add(ingredient);
            burger.addIngredient(ingredient);
        }

        int indexOfIngredient = (int) (Math.random() * numberOfIngredients);
        int newIndexOfIngredient;
        do {
            newIndexOfIngredient = (int) (Math.random() * numberOfIngredients);
        } while (indexOfIngredient == newIndexOfIngredient);

        expectedIngredients.add(newIndexOfIngredient, expectedIngredients.remove(indexOfIngredient));
        burger.moveIngredient(indexOfIngredient, newIndexOfIngredient);

        assertEquals(expectedIngredients, actualIngredients);
    }

    @Test
    public void getPriceReturnsBurgerPrice() {
        burger.setBuns(bunMock);
        addSomeIngredients(numberOfIngredients);

        float actualPrice = burger.getPrice();
        float expectedPrice = calculatePrice(numberOfIngredients);

        assertEquals(expectedPrice, actualPrice, 0.001f);
    }

    @Test
    public void getReceiptReturnsBurgerReceipt() {
        addSomeIngredients(numberOfIngredients);

        StringBuilder stringBuilder = new StringBuilder(String.format("(==== %s ====)%n", bunMock.getName()));
        for (int i = 0; i < numberOfIngredients; i++) {
            stringBuilder.append(String.format("= %s %s =%n", ingredientType.toString().toLowerCase(),
                    ingredientName));
        }
        stringBuilder.append(String.format("(==== %s ====)%n", bunName));
        stringBuilder.append(String.format("%nPrice: %f%n", calculatePrice(numberOfIngredients)));
        String expectedReceipt = stringBuilder.toString();

        burger.setBuns(bunMock);
        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    private void addSomeIngredients(int numberOfIngredients) {
        for (int i = 0; i < numberOfIngredients; i++) {
            burger.addIngredient(ingredientMock);
        }
    }

    private float calculatePrice(int numberOfIngredients) {
        return bunPrice * 2 + ingredientPrice * numberOfIngredients;
    }
}