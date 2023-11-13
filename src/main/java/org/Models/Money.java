package org.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    public BigDecimal amount;

    public Money(String amount){
        this.amount = new BigDecimal(amount);
    }

    public Money(BigDecimal amount){
        this.amount = amount;
    }

    public void add(BigDecimal toAdd) {
        amount = amount.add(toAdd);
    }
    public void add(Money toAdd) {
        amount = amount.add(toAdd.amount);
    }

    public void subtract(BigDecimal toSubtract) {
        amount = amount.subtract(toSubtract);
    }

    public BigDecimal getDiscount(int percentage) {
        //Finds given percent of the number then rounds down to up to give maximum discount
        return amount.multiply(new BigDecimal(percentage)).divide(new BigDecimal("100"),2, RoundingMode.CEILING);
    }

    public String toFormattedString() {
        if(amount.compareTo(new BigDecimal("0.99"))>0) {
            return "£"+amount.toString();
        }
        return amount.movePointRight(2).toString()+"p";
    }
}
