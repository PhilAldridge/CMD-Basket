package org.Models;

import java.math.BigDecimal;

public class DiscountDetails {
    public String name;
    public BigDecimal amount;

    public DiscountDetails(String name, BigDecimal amount){
        this.name = name;
        this.amount = amount;
    }
}
