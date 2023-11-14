package Basket;
import org.Models.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MoneyTests {
    private Money money;
    @Test
    public void canCreateMoneyClass() {
        money = new Money("1.00");
        assertEquals(new BigDecimal("1.00"),money.amount);
    }

    @Test
    public void canAddToMoney() {
        money = new Money("1.00");
        money.add(new Money("0.56"));
        assertEquals(new BigDecimal("1.56"), money.amount);
    }

    @Test
    public void canSubtractFromMoney() {
        money = new Money("1.00");
        money.subtract(new BigDecimal("0.56"));
        assertEquals(new BigDecimal("0.44"), money.amount);
    }

    @Test
    public void canGetDiscount() {
        money = new Money("3.00");
        assertEquals(new BigDecimal("0.12"), money.getDiscount(4));
    }

    @Test
    public void canGetDiscountWithRounding() {
        money = new Money("10.56");
        assertEquals(new BigDecimal("0.43"), money.getDiscount(4));
    }

    @Test
    public void returnStringWithPoundSign() {
        money = new Money("3.00");
        assertEquals("£3.00", money.toFormattedString());
    }

    @Test
    public void returnStringWithPenceSign() {
        money = new Money("0.25");
        assertEquals("25p", money.toFormattedString());
    }
}
