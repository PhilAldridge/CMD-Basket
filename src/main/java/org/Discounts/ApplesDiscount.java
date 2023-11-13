package org.Discounts;

import org.Models.DiscountDetails;
import org.Models.Item;
import org.Models.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class ApplesDiscount implements Discount {
    private final int percentDiscunt = 10;

    @Override
    public DiscountDetails apply(ArrayList<Item> basket) {
        Money totalToDiscount = new Money("0.00");

        //add up price of all apples in basket
        for(Item item : basket) {
            if(Objects.equals(item.name, "Apples")) {
                totalToDiscount.add(item.price);
            }
        }

        if(totalToDiscount.amount.equals(new BigDecimal("0.00"))) return null;

        BigDecimal discountAmount = totalToDiscount.getDiscount(percentDiscunt);
        return (new DiscountDetails("Apples 10% off", discountAmount));
    }
}
