package org.cupcakes;

import java.util.List;

public record Order(List<Cupcake> cupcakes, List<String> dailyCupcakes) {
    public int getPrice(Prices prices) {
        int price = 0;
        price += getCupcakesPrice(prices);
        price += dailyCupcakes.stream().mapToInt(prices::getDailyCupcakePrice).sum();
        return price;
    }

    private int getCupcakesPrice(Prices prices) {
        var sortedPrices = cupcakes.stream().mapToInt(c -> c.getPrice(prices)).sorted();
        int freeCupcakeAmount = cupcakes.size() / 6;
        return sortedPrices.skip(freeCupcakeAmount).sum();
    }
}
