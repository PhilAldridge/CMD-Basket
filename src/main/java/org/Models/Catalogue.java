package org.Models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public record Catalogue() {
    public static final Map<String, BigDecimal> itemsList = new HashMap<String, BigDecimal>();
    static {
        itemsList.put("Soup", new BigDecimal("0.65"));
        itemsList.put("Bread", new BigDecimal("0.80"));
        itemsList.put("Milk", new BigDecimal("1.30"));
        itemsList.put("Apples", new BigDecimal("1.00"));
    }
}
