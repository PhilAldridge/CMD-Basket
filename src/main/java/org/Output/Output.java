package org.Output;

import org.Models.DiscountDetails;
import org.Models.Money;

import java.util.ArrayList;

public interface Output {
    void generate(Money subTotal, ArrayList<DiscountDetails> discounts, Money total);
    void noItems();
    void invalidBasket();
}
