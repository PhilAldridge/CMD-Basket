package Basket;

import org.Discounts.ApplesDiscount;
import org.Discounts.BreadDiscount;
import org.Models.DiscountDetails;
import org.Models.Item;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DiscountTests {
    private final ApplesDiscount applesDiscount = new ApplesDiscount();
    private final BreadDiscount breadDiscount = new BreadDiscount();
    private final Item apples = new Item("Apples",new BigDecimal("1.00"));
    private final Item cheapApples = new Item("Apples",new BigDecimal("0.79"));
    private final Item milk = new Item("Milk",new BigDecimal("1.30"));
    private final Item bread = new Item("Bread",new BigDecimal("0.80"));
    private final Item cheapBread = new Item("Bread",new BigDecimal("0.55"));
    private final Item soup = new Item("Soup",new BigDecimal("0.65"));
    @Test
    public void noApples(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(milk);
        basket.add(soup);
        DiscountDetails actual = applesDiscount.apply(basket);
        assertNull(actual);
    }

    @Test
    public void oneApplesNoRounding(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(milk);
        basket.add(soup);
        basket.add(apples);
        DiscountDetails actual = applesDiscount.apply(basket);
        assertEquals("Apples 10% off",actual.name);
        assertEquals(new BigDecimal("0.10"), actual.amount);
    }
    @Test
    public void oneApplesRounding(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(cheapApples);
        basket.add(soup);
        DiscountDetails actual = applesDiscount.apply(basket);
        assertEquals("Apples 10% off",actual.name);
        assertEquals(new BigDecimal("0.08"), actual.amount);
    }
    @Test
    public void multipleApplesRounding(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(cheapApples);
        basket.add(soup);
        basket.add(apples);
        basket.add(soup);
        basket.add(apples);
        DiscountDetails actual = applesDiscount.apply(basket);
        assertEquals("Apples 10% off",actual.name);
        assertEquals(new BigDecimal("0.28"), actual.amount);
    }

    @Test
    public void noBread(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(milk);
        basket.add(soup);
        basket.add(bread);
        DiscountDetails actual = breadDiscount.apply(basket);
        assertNull(actual);
    }

    @Test
    public void oneBreadDiscount(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(milk);
        basket.add(soup);
        basket.add(bread);
        basket.add(soup);
        DiscountDetails actual = breadDiscount.apply(basket);
        assertEquals("Buy two tins of soup and get a loaf of bread half price",actual.name);
        assertEquals(new BigDecimal("0.40"),actual.amount);
    }
    @Test
    public void twoBreadDiscount(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(milk);
        basket.add(soup);
        basket.add(bread);
        basket.add(soup);
        basket.add(soup);
        basket.add(soup);
        basket.add(soup);
        basket.add(bread);
        basket.add(bread);
        DiscountDetails actual = breadDiscount.apply(basket);
        assertEquals("Buy two tins of soup and get a loaf of bread half price",actual.name);
        assertEquals(new BigDecimal("0.80"),actual.amount);
    }

    @Test
    public void twoBreadDiscountButNotEnoughBread(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(milk);
        basket.add(soup);
        basket.add(bread);
        basket.add(soup);
        basket.add(soup);
        basket.add(soup);
        basket.add(soup);
        DiscountDetails actual = breadDiscount.apply(basket);
        assertEquals("Buy two tins of soup and get a loaf of bread half price",actual.name);
        assertEquals(new BigDecimal("0.40"),actual.amount);
    }

    @Test
    public void breadWithRounding(){
        ArrayList<Item> basket = new ArrayList<>();
        basket.add(milk);
        basket.add(soup);
        basket.add(cheapBread);
        basket.add(soup);
        basket.add(soup);
        basket.add(soup);
        basket.add(soup);
        DiscountDetails actual = breadDiscount.apply(basket);
        assertEquals("Buy two tins of soup and get a loaf of bread half price",actual.name);
        assertEquals(new BigDecimal("0.28"),actual.amount);
    }
}
