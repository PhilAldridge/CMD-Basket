package Basket;

import org.Basket.PriceBasket;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BasketTests {
    // Variables for capturing printed output
    private final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
    private final java.io.PrintStream originalOut = System.out;
    @Test
    public void testBasketNoArgs() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{});
        System.setOut(originalOut);

        String expectedOutput = "No item arguments provided."+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketInvlaid() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Apds"});
        System.setOut(originalOut);

        String expectedOutput = "Invalid item provided" +System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketApples() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Apples"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £1.00" +System.lineSeparator()+
                "Apples 10% off: -10p"+System.lineSeparator() +
                "Total: 90p"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketSoup() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Soup"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: 65p" +System.lineSeparator()+
                "(no offers available)"+System.lineSeparator() +
                "Total: 65p"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketBread() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Bread"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: 80p" +System.lineSeparator()+
                "(no offers available)"+System.lineSeparator() +
                "Total: 80p"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketMilk() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Milk"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £1.30" +System.lineSeparator()+
                "(no offers available)"+System.lineSeparator() +
                "Total: £1.30"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketMultipleItemsNoOffers() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Milk", "Bread", "Bread", "Soup"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £3.55" +System.lineSeparator()+
                "(no offers available)"+System.lineSeparator() +
                "Total: £3.55"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketMultipleItemsAppleOffer() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Milk", "Bread", "Bread", "Soup", "Apples"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £4.55" +System.lineSeparator()+
                "Apples 10% off: -10p"+System.lineSeparator() +
                "Total: £4.45"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketMultipleItemsManyApplesOffer() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Milk", "Bread", "Bread", "Soup", "Apples", "Apples", "Apples"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £6.55" +System.lineSeparator()+
                "Apples 10% off: -30p"+System.lineSeparator() +
                "Total: £6.25"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketMultipleItemsSoupOffer() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Milk", "Bread", "Bread", "Soup","Soup", "Apples", "Apples", "Apples"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £7.20" +System.lineSeparator()+
                "Apples 10% off: -30p"+System.lineSeparator() +
                "Buy two tins of soup and get a loaf of bread half price: -40p"+System.lineSeparator() +
                "Total: £6.50"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketMultipleItemsTwoSoupOffer() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Milk", "Bread", "Bread", "Soup","Soup","Soup","Soup", "Apples", "Apples", "Apples"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £8.50" +System.lineSeparator()+
                "Apples 10% off: -30p"+System.lineSeparator() +
                "Buy two tins of soup and get a loaf of bread half price: -80p"+System.lineSeparator() +
                "Total: £7.40"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketMultipleItemsTwoSoupNotEnoughBreadOffer() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Milk", "Bread", "Soup","Soup","Soup","Soup", "Apples", "Apples", "Apples"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £7.70" +System.lineSeparator()+
                "Apples 10% off: -30p"+System.lineSeparator() +
                "Buy two tins of soup and get a loaf of bread half price: -40p"+System.lineSeparator() +
                "Total: £7.00"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBasketMultipleItemsTwoSoupNoBreadOffer() {
        System.setOut(new java.io.PrintStream(outContent));
        PriceBasket.main(new String[]{"Milk", "Soup","Soup","Soup","Soup", "Apples", "Apples", "Apples"});
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £6.90" +System.lineSeparator()+
                "Apples 10% off: -30p"+System.lineSeparator() +
                "Total: £6.60"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}
