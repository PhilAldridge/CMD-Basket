package org.Basket;

import org.Discounts.Discount;
import org.Catalogue.Catalogue;
import org.Models.DiscountDetails;
import org.Models.Item;
import org.Models.Money;
import java.util.ArrayList;

public class Basket {
    public ArrayList<Item> items= new ArrayList<>();
    public Catalogue catalogue;

    public Discount[] offers;

    public Basket(Catalogue catalogue, Discount[] offers) {
        this.catalogue = catalogue;
        this.offers = offers;
    }

    public void populateBasket(String[] args) {

        ArrayList<Item> basket = new ArrayList<>();
        for(String arg: args) {
            basket.add(new Item(arg, catalogue.getPrice(arg)));
        }
        items = basket;
    }

    public Money getSubtotal() {
        Money subTotal = new Money("0.00");
        for(Item item : items) {
            subTotal.add(item.price);
        }
        return subTotal;
    }

    public ArrayList<DiscountDetails> getDiscounts() {
        ArrayList<DiscountDetails> discountsApplied = new ArrayList<>();
        for(Discount discount: offers) {
            discountsApplied.add(discount.apply(items));
        }

        return discountsApplied;
    }
}
