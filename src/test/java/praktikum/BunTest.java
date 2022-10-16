package praktikum;

import org.junit.Before;
import org.junit.Test;
import praktikum.data.BunData;

import static org.junit.Assert.*;

public class BunTest {

    private Bun bun;
    private String expectedBunName = BunData.getBunName();
    private float expectedBunPrice = BunData.getBunPrice();

    @Before
    public void bunInit() {
        bun = new Bun(expectedBunName, expectedBunPrice);
    }

    @Test
    public void getNameReturnsBunName() {
        String actualBunName = bun.getName();

        assertEquals(expectedBunName, actualBunName);
    }

    @Test
    public void getPriceReturnsBunPrice() {
        float actualBunPrice = bun.getPrice();

        assertEquals(expectedBunPrice, actualBunPrice, 0.001f);
    }
}