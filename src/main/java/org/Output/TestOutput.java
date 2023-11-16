package org.Output;

import org.Models.DiscountDetails;
import org.Models.Money;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TestOutput implements Output{
    public BigDecimal subTotal, total;
    public ArrayList<DiscountDetails> discountsApplied;
    public boolean noItems, invalidItems = false;

    @Override
    public void generate(Money subTotal, ArrayList<DiscountDetails> discounts, Money total) {
        this.subTotal = subTotal.amount;
        this.total = total.amount;
        this.discountsApplied = discounts;
    }
    @Override
    public void invalidBasket() {
        invalidItems = true;
    }
    @Override
    public void noItems() {
        noItems=true;
    }

    public void reset(){
        invalidItems = false;
        noItems = false;
    }
}
