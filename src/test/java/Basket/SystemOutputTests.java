package Basket;

import org.Models.DiscountDetails;
import org.Models.Money;
import org.Output.Output;
import org.Output.SystemOutput;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
public class SystemOutputTests {
    // Variables for capturing printed output
    private final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
    private final java.io.PrintStream originalOut = System.out;
    private final Output output = new SystemOutput();
    @Test
    public void testOutputNoArgs() {
        System.setOut(new java.io.PrintStream(outContent));
        output.noItems();
        System.setOut(originalOut);

        String expectedOutput = "No item arguments provided."+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testOutputInvalid() {
        System.setOut(new java.io.PrintStream(outContent));
        output.invalidBasket();
        System.setOut(originalOut);

        String expectedOutput = "Invalid item provided" +System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testOutputOneDiscount() {
        System.setOut(new java.io.PrintStream(outContent));
        Money subTotal = new Money("1.00");
        ArrayList<DiscountDetails> discounts = new ArrayList<>();
        discounts.add(new DiscountDetails("Apples 10% off",new BigDecimal("0.10")));
        discounts.add(null);
        Money total = new Money("0.90");
        output.generate(subTotal,discounts,total);
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £1.00" +System.lineSeparator()+
                "Apples 10% off: -10p"+System.lineSeparator() +
                "Total: 90p"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testOutputNoDiscounts() {
        System.setOut(new java.io.PrintStream(outContent));
        Money subTotal = new Money("0.65");
        ArrayList<DiscountDetails> discounts = new ArrayList<>();
        discounts.add(null);
        discounts.add(null);
        Money total = new Money("0.65");
        output.generate(subTotal,discounts,total);
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: 65p" +System.lineSeparator()+
                "(no offers available)"+System.lineSeparator() +
                "Total: 65p"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testOutputNoOffers() {
        System.setOut(new java.io.PrintStream(outContent));
        Money subTotal = new Money("0.65");
        ArrayList<DiscountDetails> discounts = new ArrayList<>();
        Money total = new Money("0.65");
        output.generate(subTotal,discounts,total);
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: 65p" +System.lineSeparator()+
                "(no offers available)"+System.lineSeparator() +
                "Total: 65p"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testOutputMultipleOffers() {
        System.setOut(new java.io.PrintStream(outContent));
        Money subTotal = new Money("1.00");
        ArrayList<DiscountDetails> discounts = new ArrayList<>();
        discounts.add(new DiscountDetails("Apples 10% off",new BigDecimal("0.10")));
        discounts.add(new DiscountDetails("Second offer",new BigDecimal("1.55")));
        Money total = new Money("0.90");
        output.generate(subTotal,discounts,total);
        System.setOut(originalOut);

        String expectedOutput = "Subtotal: £1.00" +System.lineSeparator()+
                "Apples 10% off: -10p"+System.lineSeparator() +
                "Second offer: -£1.55"+System.lineSeparator() +
                "Total: 90p"+System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

}
