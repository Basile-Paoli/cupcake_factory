package org.cupcakes;

import java.util.HashMap;

public class IngredientStorage {
    HashMap<CupcakeBase, Integer> getBases() {
        return new HashMap<>();
    }

    HashMap<Topping, Integer> getToppings() {
        return new HashMap<>();
    }

    HashMap<Cream, Integer> getCreams() {
        return new HashMap<>();
    }

    void addBase(CupcakeBase base, int quantity) {
    }

    void addTopping(Topping topping, int quantity) {
    }

    void addCream(Cream cream, int quantity) {
    }

    void removeBase(CupcakeBase base, int quantity) {
    }

    void removeTopping(Topping topping, int quantity) {
    }

    void removeCream(Cream cream, int quantity) {
    }
}
