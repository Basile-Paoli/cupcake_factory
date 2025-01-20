package org.cupcakes;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public record Prices(Map<Cream, Integer> creamPrices, Map<CupcakeBase, Integer> basePrices,
                     Map<Topping, Integer> toppingPrices, Map<String, Integer> dailyCupcakePrices) {
    public Prices {
        if (creamPrices.entrySet().stream().anyMatch((p) -> p.getValue() < 0)) {
            throw new IllegalArgumentException("Cream prices must be non-negative");
        }

        if (basePrices.entrySet().stream().anyMatch((p) -> p.getValue() < 0)) {
            throw new IllegalArgumentException("Base prices must be non-negative");
        }

        if (toppingPrices.entrySet().stream().anyMatch((p) -> p.getValue() < 0)) {
            throw new IllegalArgumentException("Topping prices must be non-negative");
        }

        if (dailyCupcakePrices.entrySet().stream().anyMatch((p) -> p.getValue() < 0)) {
            throw new IllegalArgumentException("Daily cupcake prices must be non-negative");
        }
    }

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
