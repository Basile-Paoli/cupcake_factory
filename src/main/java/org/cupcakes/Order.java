package org.cupcakes;

import java.util.List;

public record Order(List<Cupcake> cupcakes, List<String> dailyCupcakes) {
    public int getTotalPrice(Prices prices) {
        int price = 0;
        price += cupcakes.stream().mapToInt((c) -> c.getPrice(prices)).sum();
        price += dailyCupcakes.stream().mapToInt(prices::getDailyCupcakePrice).sum();
        return price;
    }
}
