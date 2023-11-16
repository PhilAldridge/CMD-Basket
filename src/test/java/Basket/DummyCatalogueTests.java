package Basket;

import org.Catalogue.Catalogue;
import org.Catalogue.DummyCatalogue;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DummyCatalogueTests {
    private final Catalogue catalogue = new DummyCatalogue();

    @Test
    public void getItemPrice() {
        BigDecimal actual = catalogue.getPrice("Apples");
        BigDecimal expected = new BigDecimal("1.00");
        assertEquals(expected,actual);
    }

    @Test
    public void itemExists() {
        assertTrue(catalogue.itemExists("Apples"));
    }

    @Test
    public void itemDoesntExist() {
        assertFalse(catalogue.itemExists("sdf"));
    }

    @Test
    public void testMultipleValidItems() {
        assertTrue(catalogue.validItemList(new String[]{"Apples", "Apples", "Milk", "Bread"}));
    }

    @Test
    public void testMultipleItemsIncInvalid(){
        assertFalse(catalogue.validItemList(new String[]{"Apples","Apples", "Milk", "sdf", "sdfd"}));
    }
}
