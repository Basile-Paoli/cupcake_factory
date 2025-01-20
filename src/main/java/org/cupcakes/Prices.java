package org.cupcakes;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public record Prices(Map<Cream, Integer> creamPrices, Map<CupcakeBase, Integer> basePrices,
                     Map<Topping, Integer> toppingPrices, Map<String, Integer> dailyCupcakePrices) {
    public int getCreamPrice(Cream cream) {
        return creamPrices.get(cream);
    }

    public int getToppingPrice(Topping topping) {
        return toppingPrices.get(topping);
    }

    public int getBasePrice(CupcakeBase base) {
        return basePrices.get(base);
    }

    public int getDailyCupcakePrice(String dailyCupcake) {
        return dailyCupcakePrices.get(dailyCupcake);
    }


}
