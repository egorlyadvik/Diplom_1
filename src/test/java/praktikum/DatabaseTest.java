package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//Добавил тесты базы данных, т.к. без них не выполняется требование в 70% покрытия кода
public class DatabaseTest {

    private Database database;

    @Before
    public void databaseInit() {
        database = new Database();
    }

    @Test
    public void availableBunsReturnsListOfBuns() {
        assertNotNull(database.availableBuns());
    }

    @Test
    public void availableIngredientsReturnsListOfIngredients() {
        assertNotNull(database.availableIngredients());
    }
}