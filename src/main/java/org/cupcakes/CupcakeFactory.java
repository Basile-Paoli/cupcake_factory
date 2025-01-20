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

    private List<MenuEntry<Cream>> getAvailableCreams() {
        HashMap<Cream, Integer> creams = storage.getCreams();
        return creams.entrySet().stream().filter(e -> e.getValue() > 0)
                .map(e -> new MenuEntry<>(e.getKey(), this.prices.getCreamPrice(e.getKey()))).toList();
    }

    private List<MenuEntry<CupcakeBase>> getAvailableBases() {
        HashMap<CupcakeBase, Integer> bases = storage.getBases();
        return bases.entrySet().stream().filter(e -> e.getValue() > 0)
                .map(e -> new MenuEntry<>(e.getKey(), this.prices.getBasePrice(e.getKey()))).toList();
    }

    private List<MenuEntry<Topping>> getAvailableToppings() {
        HashMap<Topping, Integer> toppings = storage.getToppings();
        return toppings.entrySet().stream().filter(e -> e.getValue() > 0)
                .map(e -> new MenuEntry<>(e.getKey(), this.prices.getToppingPrice(e.getKey()))).toList();
    }

    private boolean hasEnoughIngredientsForCupcake() {
        return !this.getAvailableBases().isEmpty() && !this.getAvailableCreams().isEmpty() && !this.getAvailableToppings().isEmpty();
    }

    public CupcakeMenu getMenu() {
        if (!hasEnoughIngredientsForCupcake()) {
            return new CupcakeMenu(List.of(), List.of(), List.of(), List.of());
        }
        return new CupcakeMenu(this.getAvailableCreams(), this.getAvailableBases(), this.getAvailableToppings(), List.of());
    }
}
