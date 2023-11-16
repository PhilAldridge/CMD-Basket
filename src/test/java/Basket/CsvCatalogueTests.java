package Basket;

import com.opencsv.exceptions.CsvException;
import org.Catalogue.Catalogue;
import org.Catalogue.CsvCatalogue;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CsvCatalogueTests {
    private final Catalogue catalogue = new CsvCatalogue("data.csv");

    public CsvCatalogueTests() throws IOException, CsvException {
    }

    @Test
    public void getItemPrice() {
        BigDecimal actual = catalogue.getPrice("Milk");
        assertTrue(actual.compareTo(new BigDecimal("0.00"))>0);
    }

    @Test
    public void itemExists() {
        assertTrue(catalogue.itemExists("Milk"));
    }

    @Test
    public void itemDoesntExist() {
        assertFalse(catalogue.itemExists("sdfwdfwsdfgsdfg"));
    }

    @Test
    public void testMultipleItemsIncInvalid(){
        assertFalse(catalogue.validItemList(new String[]{"Milk", "sdf", "sdfd"}));
    }
}
