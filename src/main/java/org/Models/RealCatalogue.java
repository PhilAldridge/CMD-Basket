package org.Models;

import java.math.BigDecimal;

public class RealCatalogue extends Catalogue{
    public RealCatalogue() {
        itemsList.put("Soup", new BigDecimal("0.65"));
        itemsList.put("Bread", new BigDecimal("0.80"));
        itemsList.put("Milk", new BigDecimal("1.30"));
        itemsList.put("Apples", new BigDecimal("1.00"));
    }
}
