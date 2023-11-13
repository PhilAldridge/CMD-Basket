package org.Basket;

import org.Discounts.ApplesDiscount;
import org.Discounts.BreadDiscount;
import org.Discounts.Discount;
import org.Models.DiscountDetails;
import org.Models.Item;
import java.util.ArrayList;

public class Offers {
    private final ArrayList<Discount> discountsToApply;
    public Offers() {
        discountsToApply = new ArrayList<>();
        discountsToApply.add(new ApplesDiscount());
        discountsToApply.add(new BreadDiscount());
    }
    public ArrayList<DiscountDetails> getApplicable(ArrayList<Item> basket) {
        ArrayList<DiscountDetails> discountsApplied = new ArrayList<>();
        for(Discount discount: discountsToApply) {
            discountsApplied.add(discount.apply(basket));
        }

        return discountsApplied;
    }


}
