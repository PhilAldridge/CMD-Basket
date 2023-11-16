package org.Basket;

import com.opencsv.exceptions.CsvException;
import org.Discounts.ApplesDiscount;
import org.Discounts.BreadDiscount;
import org.Discounts.Discount;
import org.Catalogue.Catalogue;
import org.Models.DiscountDetails;
import org.Models.Money;
import org.Catalogue.CsvCatalogue;

import java.io.IOException;
import java.util.ArrayList;

public class PriceBasket {

    public static void main(String[] args) {
        try {
            Catalogue catalogue = new CsvCatalogue();
            outPutBasketPrice(args, catalogue, new Discount[]{new ApplesDiscount(), new BreadDiscount()});
        } catch (IOException | CsvException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void outPutBasketPrice(String[] args, Catalogue catalogue, Discount[] discounts) {
        if (validInput(args, catalogue)) return;
        //Setup basket
        Basket basket = new Basket(catalogue,discounts);
        basket.populateBasket(args);

        //Calculate and Print subTotal
        Money subTotal = basket.getSubtotal();
        printLine("Subtotal: "+subTotal.toFormattedString());

        //Calculate and Print discounts
        ArrayList<DiscountDetails> discountsToApply = basket.getDiscounts();
        printDiscounts(discountsToApply);

        //Calculate and Print Total
        Money total = calculateTotal(subTotal,discountsToApply);
        printLine("Total: " + total.toFormattedString());
    }

    private static boolean validInput(String[] args, Catalogue catalogue) {
        if(args.length==0){
            printLine("No item arguments provided.");
            return true;
        }
        if(!catalogue.validItemList(args)){
            printLine("Invalid item provided");
            return true;
        }
        return false;
    }

    private static void printDiscounts(ArrayList<DiscountDetails> discounts) {
        boolean discountsApplied = false;
        for(DiscountDetails discount: discounts) {
            if(discount == null){
                continue;
            }
            discountsApplied = true;
            Money discountAmount = new Money(discount.amount);
            printLine(discount.name+": -"+discountAmount.toFormattedString());
        }
        if(!discountsApplied) {
            printLine("(no offers available)");
        }
    }

    private static Money calculateTotal(Money subTotal, ArrayList<DiscountDetails> discounts) {
        Money total = new Money(subTotal.amount);
        for(DiscountDetails discount: discounts) {
            if(discount != null) {
                total.subtract(discount.amount);
            }
        }
        return total;
    }

    private static void printLine(String string) {
        System.out.println(string);
    }
}
