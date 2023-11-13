package org.Discounts;

import org.Models.DiscountDetails;
import org.Models.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class BreadDiscount implements Discount{

    @Override
    public DiscountDetails apply(ArrayList<Item> basket) {
        int soups = 0;
        ArrayList<BigDecimal> amountsToDiscount = new ArrayList<>();
        for(Item item: basket) {
            switch (item.name) {
                case "Soup":
                    soups++;
                    break;
                case "Bread":
                    amountsToDiscount.add(item.price);
                    break;
                default:
                    break;
            }
        }
        if(soups<2 || amountsToDiscount.isEmpty()) return null;

        int iterations = Math.floorDiv(soups,2);

        BigDecimal discountAmount = findSoupBreadDiscountAmount(amountsToDiscount,iterations);

        return (new DiscountDetails("Buy two tins of soup and get a loaf of bread half price", discountAmount));
    }

    private BigDecimal findSoupBreadDiscountAmount(ArrayList<BigDecimal> amounts, int iterations){
        //Halves the price of the most expensive loaf of bread.
        // Not necessary here, but added for extensibility after more bread items are added.
        BigDecimal totalDiscount = new BigDecimal("0.00");
        for(int i=0; i<iterations; i++ ){
            int index = indexOfMostExpensiveBigDecimal(amounts);
            //Rounds up to apply the maximum discount
            BigDecimal discount = amounts.get(index).divide(new BigDecimal("2"),2, RoundingMode.CEILING);
            amounts.remove(index);
            totalDiscount = totalDiscount.add(discount);
            if(amounts.isEmpty()) return totalDiscount;
        }
        return totalDiscount;
    }

    private int indexOfMostExpensiveBigDecimal(ArrayList<BigDecimal> amounts) {
        BigDecimal max = new BigDecimal("0.00");
        int maxIndex = 0;
        for (int i = 0; i < amounts.size(); i++) {
            BigDecimal amount = amounts.get(i);
            if (amount.compareTo(max) > 0) {
                max = amount;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
