package org.cupcakes;

import java.util.HashMap;
import java.util.List;

public class CupcakeFactory {
    private final IngredientStorage storage;
    private final Prices prices;

    public CupcakeFactory(IngredientStorage storage, Prices prices) {
        this.storage = storage;
        this.prices = prices;
    }

    public CupcakeMenu getMenu() {
        if (!hasEnoughIngredientsForCupcake()) {
            return new CupcakeMenu(List.of(), List.of(), List.of(), List.of());
        }
        return new CupcakeMenu(getAvailableCreams(), getAvailableBases(), getAvailableToppings(), getAvailableDailyCupcakes());
    }

    public void orderCupcakes(Order order) {
        for (Cupcake cupcake : order.cupcakes()) {
            storage.removeBase(cupcake.base(), 1);
            storage.removeCream(cupcake.cream(), 1);
            for (Topping topping : cupcake.toppings()) {
                storage.removeTopping(topping, 1);
            }
        }

        for (String dailyCupcake : order.dailyCupcakes()) {
            storage.removeCupcakeDay(dailyCupcake, 1);
        }
    }

    private List<MenuEntry<Cream>> getAvailableCreams() {
        HashMap<Cream, Integer> creams = storage.getCreams();
        return creams.entrySet().stream().filter(e -> e.getValue() > 0)
                .map(e -> new MenuEntry<>(e.getKey(), prices.getCreamPrice(e.getKey()))).toList();
    }

    private List<MenuEntry<CupcakeBase>> getAvailableBases() {
        HashMap<CupcakeBase, Integer> bases = storage.getBases();
        return bases.entrySet().stream().filter(e -> e.getValue() > 0)
                .map(e -> new MenuEntry<>(e.getKey(), prices.getBasePrice(e.getKey()))).toList();
    }

    private List<MenuEntry<Topping>> getAvailableToppings() {
        HashMap<Topping, Integer> toppings = storage.getToppings();
        return toppings.entrySet().stream().filter(e -> e.getValue() > 0)
                .map(e -> new MenuEntry<>(e.getKey(), prices.getToppingPrice(e.getKey()))).toList();
    }

    private List<MenuEntry<String>> getAvailableDailyCupcakes() {
        HashMap<String, Integer> dailyCupcakes = storage.getCupcakesDay();
        return dailyCupcakes.entrySet().stream().filter(e -> e.getValue() > 0)
                .map(e -> new MenuEntry<>(e.getKey(), prices.getDailyCupcakePrice(e.getKey()))).toList();
    }

    private boolean hasEnoughIngredientsForCupcake() {
        return !getAvailableBases().isEmpty() && !getAvailableCreams().isEmpty() && !getAvailableToppings().isEmpty();
    }

}
