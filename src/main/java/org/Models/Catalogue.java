package org.Models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Catalogue {
    final Map<String, BigDecimal> itemsList = new HashMap<>();
    public boolean itemExists(String item){
        return itemsList.containsKey(item);
    }
    public BigDecimal getPrice(String item) {
        return itemsList.get(item);
    }

    public boolean validItemList(String[] args) {
        for(String item: args) {
            if(!itemsList.containsKey(item)) return false;
        }
        return true;
    }
}
