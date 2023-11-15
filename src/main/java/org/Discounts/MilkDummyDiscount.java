package org.Discounts;

import org.Models.DiscountDetails;
import org.Models.Item;
import org.Models.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class MilkDummyDiscount implements Discount {
    private final int percentDiscount = 15;

    @Override
    public DiscountDetails apply(ArrayList<Item> basket) {
        Money totalToDiscount = new Money("0.00");

        //add up price of all apples in basket
        for(Item item : basket) {
            if(Objects.equals(item.name, "Milk")) {
                totalToDiscount.add(item.price);
            }
        }

        if(totalToDiscount.amount.equals(new BigDecimal("0.00"))) return null;

        BigDecimal discountAmount = totalToDiscount.getDiscount(percentDiscount);
        return (new DiscountDetails("Milk "+percentDiscount+ "% off", discountAmount));
    }
}
