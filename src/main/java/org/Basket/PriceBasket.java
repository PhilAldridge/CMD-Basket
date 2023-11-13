package org.Basket;

import org.Models.Catalogue;
import org.Models.DiscountDetails;
import org.Models.Item;
import org.Models.Money;
import java.util.ArrayList;

public class PriceBasket {

    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("No item arguments provided.");
            return;
        }
        if (invalidItemCalled(args)) return;

        ArrayList<Item> basket = populateBasket(args);

        //Calculate and output subtotal
        Money subTotal = calculateSubtotal(basket);
        System.out.println("Subtotal: "+subTotal.toFormattedString());

        //Get list of applicable discounts
        Offers offers = new Offers();
        ArrayList<DiscountDetails> discountsToApply = offers.getApplicable(basket);

        //Calculate Final Total After Discounts
        applyDiscounts(subTotal, discountsToApply);
    }
    private static boolean invalidItemCalled(String[] args) {
        for(String arg: args) {
            if(!Catalogue.itemsList.containsKey(arg)){
                System.out.println("Invalid item provided");
                return true;
            }
        }
        return false;
    }
    private static ArrayList<Item> populateBasket(String[] args) {
        ArrayList<Item> basket = new ArrayList<>();
        for(String arg: args) {
            basket.add(new Item(arg, Catalogue.itemsList.get(arg)));
        }
        return basket;
    }
    private static Money calculateSubtotal(ArrayList<Item> basket){
        Money subTotal = new Money("0.00");
        for(Item item: basket){
            subTotal.add(item.price);
        }
        return subTotal;
    }
    private static void applyDiscounts(Money total, ArrayList<DiscountDetails> discountsApplied) {
        boolean discountsUsed = false;

        for(DiscountDetails discountDetails : discountsApplied) {
            if(discountDetails == null){
                continue;
            }
            discountsUsed = true;
            Money discountAmount = new Money(discountDetails.amount);
            System.out.println(discountDetails.name+": -"+discountAmount.toFormattedString());
            total.subtract(discountDetails.amount);
        }
        if(!discountsUsed) {
            System.out.println("(no offers available)");
        }

        System.out.println("Total: " + total.toFormattedString());
    }
}
