package org.Discounts;

import org.Models.DiscountDetails;
import org.Models.Item;

import java.util.ArrayList;

public interface Discount {
    public DiscountDetails apply(ArrayList<Item> basket);
}
