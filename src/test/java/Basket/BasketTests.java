package Basket;

import org.Basket.Basket;
import org.Discounts.ApplesDiscount;
import org.Discounts.BreadDiscount;
import org.Discounts.Discount;
import org.Discounts.MilkDummyDiscount;
import org.Models.DiscountDetails;
import org.Catalogue.DummyCatalogue;
import org.Models.Item;
import org.Models.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BasketTests {
    private final Discount[] offers = {new ApplesDiscount(), new BreadDiscount()};
    private final Basket basket = new Basket(new DummyCatalogue(), offers);

    @Test
    public void initialiseBasket(){
        BigDecimal actual = basket.catalogue.getPrice("Apples");
        assertEquals(new BigDecimal("1.00"),actual);
    }

    @Test
    public void populateBasketOneItem() {
        basket.populateBasket(new String[]{"Apples"});
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(new Item("Apples",new BigDecimal("1.00")));
        assertEquals(expected.get(0).name,basket.items.get(0).name);
        assertEquals(expected.get(0).price,basket.items.get(0).price);
    }

    @Test
    public void populateBasket() {
        basket.populateBasket(new String[]{"Apples","Apples","Milk","Bread"});
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(new Item("Apples",new BigDecimal("1.00")));
        expected.add(new Item("Apples",new BigDecimal("1.00")));
        expected.add(new Item("Milk",new BigDecimal("1.30")));
        expected.add(new Item("Bread",new BigDecimal("0.80")));
        for(int i = 0; i<basket.items.size();i++){
            assertEquals(expected.get(i).name,basket.items.get(i).name);
            assertEquals(expected.get(i).price,basket.items.get(i).price);
        }
    }

    @Test
    public void getSubtotal(){
        basket.populateBasket(new String[]{"Apples","Apples","Milk","Bread"});
        Money actual = basket.getSubtotal();
        Money expected = new Money("4.10");
        assertEquals(expected.amount,actual.amount);
    }

    @Test
    public void noDiscountToApply() {
        basket.populateBasket(new String[]{"Bread","Milk","Bread"});
        ArrayList<DiscountDetails> discounts = basket.getDiscounts();
        for(DiscountDetails discount: discounts) {
            assertNull(discount);
        }
    }

    @Test
    public void singleAppleDiscountToApply() {
        basket.populateBasket(new String[]{"Bread","Milk","Bread", "Apples", "Soup"});
        ArrayList<DiscountDetails> discounts = basket.getDiscounts();
        assertEquals("Apples 10% off",discounts.get(0).name);
        assertEquals(new BigDecimal("0.10"), discounts.get(0).amount);
        assertNull(discounts.get(1));
        assertEquals(2, discounts.size());
    }

    @Test
    public void appleAndBreadDiscountToApply() {
        basket.populateBasket(new String[]{"Bread","Milk","Bread", "Apples", "Soup", "Soup", "Apples"});
        ArrayList<DiscountDetails> discounts = basket.getDiscounts();
        assertEquals("Apples 10% off",discounts.get(0).name);
        assertEquals(new BigDecimal("0.20"), discounts.get(0).amount);
        assertEquals("Buy two tins of soup and get a loaf of bread half price",discounts.get(1).name);
        assertEquals(new BigDecimal("0.40"),discounts.get(1).amount);
        assertEquals(2, discounts.size());
    }

    @Test
    public void basketChangeOfOffers() {
        Basket basket2 = new Basket(new DummyCatalogue(), new Discount[]{new MilkDummyDiscount()});
        basket2.populateBasket(new String[]{"Bread","Milk","Bread", "Apples", "Soup"});
        ArrayList<DiscountDetails> discounts = basket2.getDiscounts();
        assertEquals("Milk 15% off",discounts.get(0).name);
        assertEquals(new BigDecimal("0.20"), discounts.get(0).amount);
        assertEquals(1, discounts.size());
    }
}
