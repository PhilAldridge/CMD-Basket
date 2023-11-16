package org.Basket;

import com.opencsv.exceptions.CsvException;
import org.Discounts.ApplesDiscount;
import org.Discounts.BreadDiscount;
import org.Discounts.Discount;
import org.Catalogue.Catalogue;
import org.Models.DiscountDetails;
import org.Models.Money;
import org.Catalogue.CsvCatalogue;
import org.Output.Output;
import org.Output.SystemOutput;

import java.io.IOException;
import java.util.ArrayList;

public class PriceBasket {

    public static void main(String[] args) {
        try {
            Catalogue catalogue = new CsvCatalogue();
            Output output = new SystemOutput();
            outPutBasketPrice(args, catalogue, new Discount[]{new ApplesDiscount(), new BreadDiscount()}, output);
        } catch (IOException | CsvException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void outPutBasketPrice(String[] args, Catalogue catalogue, Discount[] discounts, Output output) {
        if (validInput(args, catalogue, output)) return;
        //Setup basket
        Basket basket = new Basket(catalogue,discounts);
        basket.populateBasket(args);

        //Calculate subTotal
        Money subTotal = basket.getSubtotal();

        //Calculate discounts
        ArrayList<DiscountDetails> discountsToApply = basket.getDiscounts();

        //Calculate Total
        Money total = calculateTotal(subTotal,discountsToApply);

        //Output results
        output.generate(subTotal,discountsToApply,total);
    }

    private static boolean validInput(String[] args, Catalogue catalogue, Output output) {
        if(args.length==0){
            output.noItems();
            return true;
        }
        if(!catalogue.validItemList(args)){
            output.invalidBasket();
            return true;
        }
        return false;
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
}
