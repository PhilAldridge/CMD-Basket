package Basket;

import org.Basket.PriceBasket;
import org.Catalogue.Catalogue;
import org.Catalogue.DummyCatalogue;
import org.Discounts.ApplesDiscount;
import org.Discounts.BreadDiscount;
import org.Discounts.Discount;
import org.Output.TestOutput;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BasketPriceTests {
    private final Catalogue catalogue = new DummyCatalogue();
    private final Discount[] discounts = new Discount[]{new ApplesDiscount(), new BreadDiscount()};
    private final TestOutput output = new TestOutput();
    @Test
    public void testBasketNoArgs() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{} , catalogue,discounts,output);
        assertTrue(output.noItems);
    }

    @Test
    public void testBasketInvalid() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Apds"}, catalogue,discounts,output);
        assertTrue(output.invalidItems);
    }

    @Test
    public void testBasketApples() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Apples"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("1.00"), output.subTotal);
        assertEquals("Apples 10% off", output.discountsApplied.get(0).name);
        assertEquals(new BigDecimal("0.10"), output.discountsApplied.get(0).amount);
        assertNull(output.discountsApplied.get(1));
        assertEquals(new BigDecimal("0.90"), output.total);
    }

    @Test
    public void testBasketSoup() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Soup"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("0.65"), output.subTotal);
        assertNull(output.discountsApplied.get(0));
        assertNull(output.discountsApplied.get(1));
        assertEquals(new BigDecimal("0.65"), output.total);
    }

    @Test
    public void testBasketBread() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Bread"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("0.80"), output.subTotal);
        assertNull(output.discountsApplied.get(0));
        assertNull(output.discountsApplied.get(1));
        assertEquals(new BigDecimal("0.80"), output.total);
    }

    @Test
    public void testBasketMilk() {
        
        PriceBasket.outPutBasketPrice(new String[]{"Milk"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("1.30"), output.subTotal);
        assertNull(output.discountsApplied.get(0));
        assertNull(output.discountsApplied.get(1));
        assertEquals(new BigDecimal("1.30"), output.total);
    }

    @Test
    public void testBasketMultipleItemsNoOffers() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Milk", "Bread", "Bread", "Soup"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("3.55"), output.subTotal);
        assertNull(output.discountsApplied.get(0));
        assertNull(output.discountsApplied.get(1));
        assertEquals(new BigDecimal("3.55"), output.total);
    }

    @Test
    public void testBasketMultipleItemsAppleOffer() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Milk", "Bread", "Bread", "Soup", "Apples"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("4.55"), output.subTotal);
        assertEquals("Apples 10% off", output.discountsApplied.get(0).name);
        assertEquals(new BigDecimal("0.10"), output.discountsApplied.get(0).amount);
        assertNull(output.discountsApplied.get(1));
        assertEquals(new BigDecimal("4.45"), output.total);
    }

    @Test
    public void testBasketMultipleItemsManyApplesOffer() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Milk", "Bread", "Bread", "Soup", "Apples", "Apples", "Apples"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("6.55"), output.subTotal);
        assertEquals("Apples 10% off", output.discountsApplied.get(0).name);
        assertEquals(new BigDecimal("0.30"), output.discountsApplied.get(0).amount);
        assertNull(output.discountsApplied.get(1));
        assertEquals(new BigDecimal("6.25"), output.total);
    }

    @Test
    public void testBasketMultipleItemsSoupAndApplesOffer() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Milk", "Bread", "Bread", "Soup","Soup", "Apples", "Apples", "Apples"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("7.20"), output.subTotal);
        assertEquals("Apples 10% off", output.discountsApplied.get(0).name);
        assertEquals(new BigDecimal("0.30"), output.discountsApplied.get(0).amount);
        assertEquals("Buy two tins of soup and get a loaf of bread half price", output.discountsApplied.get(1).name);
        assertEquals(new BigDecimal("0.40"), output.discountsApplied.get(1).amount);
        assertEquals(new BigDecimal("6.50"), output.total);
    }

    @Test
    public void testBasketMultipleItemsTwoSoupOffer() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Milk", "Bread", "Bread", "Soup","Soup","Soup","Soup", "Apples", "Apples", "Apples"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("8.50"), output.subTotal);
        assertEquals("Apples 10% off", output.discountsApplied.get(0).name);
        assertEquals(new BigDecimal("0.30"), output.discountsApplied.get(0).amount);
        assertEquals("Buy two tins of soup and get a loaf of bread half price", output.discountsApplied.get(1).name);
        assertEquals(new BigDecimal("0.80"), output.discountsApplied.get(1).amount);
        assertEquals(new BigDecimal("7.40"), output.total);
    }

    @Test
    public void testBasketMultipleItemsTwoSoupNotEnoughBreadOffer() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Milk", "Bread", "Soup","Soup","Soup","Soup", "Apples", "Apples", "Apples"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("7.70"), output.subTotal);
        assertEquals("Apples 10% off", output.discountsApplied.get(0).name);
        assertEquals(new BigDecimal("0.30"), output.discountsApplied.get(0).amount);
        assertEquals("Buy two tins of soup and get a loaf of bread half price", output.discountsApplied.get(1).name);
        assertEquals(new BigDecimal("0.40"), output.discountsApplied.get(1).amount);
        assertEquals(new BigDecimal("7.00"), output.total);
    }

    @Test
    public void testBasketMultipleItemsTwoSoupNoBreadOffer() {
        output.reset();
        PriceBasket.outPutBasketPrice(new String[]{"Milk", "Soup","Soup","Soup","Soup", "Apples", "Apples", "Apples"}, catalogue,discounts,output);

        assertEquals(new BigDecimal("6.90"), output.subTotal);
        assertEquals("Apples 10% off", output.discountsApplied.get(0).name);
        assertEquals(new BigDecimal("0.30"), output.discountsApplied.get(0).amount);
        assertNull(output.discountsApplied.get(1));
        assertEquals(new BigDecimal("6.60"), output.total);
    }
}
