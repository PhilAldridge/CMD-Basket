package org.Catalogue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class Catalogue {
    Map<String, BigDecimal> itemsList = new HashMap<>();
    public final boolean itemExists(String item){
        return itemsList.containsKey(item);
    }
    public final BigDecimal getPrice(String item) {
        return itemsList.get(item);
    }

    public final boolean validItemList(String[] args) {
        for(String item: args) {
            if(!itemsList.containsKey(item)) return false;
        }
        return true;
    }
}
