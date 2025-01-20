package org.cupcakes;

import java.util.List;

public record Cupcake(CupcakeBase base, Cream cream, List<Topping> toppings) {
    public boolean isValid() {
        return toppings.size() <= 2;
    }

    public int getPrice(Prices prices) {
        int price = 0;
        price += prices.getCreamPrice(cream);
        price += prices.getBasePrice(base);
        price += getToppingsPrice(prices);
        return price;
    }

    private int getToppingsPrice(Prices prices) {
        if (toppings.isEmpty()) {
            return 0;
        } else if (toppings.size() == 1) {
            return prices.getToppingPrice(toppings.getFirst());
        } else {
            int min = toppings.stream().mapToInt(prices::getToppingPrice).min().orElseThrow();
            return toppings.stream().mapToInt(prices::getToppingPrice).sum() - min;
        }
    }
}
