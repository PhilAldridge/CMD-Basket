package org.Output;

import org.Models.DiscountDetails;
import org.Models.Money;

import java.util.ArrayList;

public class SystemOutput implements Output{
    @Override
    public void generate(Money subTotal, ArrayList<DiscountDetails> discounts, Money total) {
        System.out.println("Subtotal: "+subTotal.toFormattedString());
        printDiscounts(discounts);
        System.out.println("Total: " + total.toFormattedString());
    }

    @Override
    public void noItems() {
        System.out.println("No item arguments provided.");
    }

    @Override
    public void invalidBasket() {
        System.out.println("Invalid item provided");
    }

    private void printDiscounts(ArrayList<DiscountDetails> discounts) {
        boolean discountsApplied = false;
        for(DiscountDetails discount: discounts) {
            if(discount == null){
                continue;
            }
            discountsApplied = true;
            Money discountAmount = new Money(discount.amount);
            System.out.println(discount.name+": -"+discountAmount.toFormattedString());
        }
        if(!discountsApplied) {
            System.out.println("(no offers available)");
        }
    }
}
