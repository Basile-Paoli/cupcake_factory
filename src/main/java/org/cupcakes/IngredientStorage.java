package org.cupcakes;

import java.util.HashMap;

public class IngredientStorage {

    private HashMap<CupcakeBase, Integer> bases = new HashMap<>();
    private HashMap<Topping, Integer> toppings = new HashMap<>();
    private HashMap<Cream, Integer> creams = new HashMap<>();

    HashMap<CupcakeBase, Integer> getBases() {
        return bases;
    }

    HashMap<Topping, Integer> getToppings() {
        return toppings;
    }

    HashMap<Cream, Integer> getCreams() {
        return creams;
    }


    void addBase(CupcakeBase base, int quantity) {

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        bases.put(base, bases.getOrDefault(base, 0) + quantity);
    }

    void addTopping(Topping topping, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        toppings.put(topping, toppings.getOrDefault(topping, 0) + quantity);
    }

    void addCream(Cream cream, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        creams.put(cream, creams.getOrDefault(cream, 0) + quantity);

    }

    void removeBase(CupcakeBase base, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        int currentQuantity = bases.getOrDefault(base, 0);

        if (currentQuantity >= quantity) {
            bases.put(base, currentQuantity - quantity);
        }
        if (bases.get(base) == 0) {

            bases.remove(base);

        }

    }

    void removeTopping(Topping topping, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        int currentQuantity = toppings.getOrDefault(topping, 0);

        if (currentQuantity >= quantity) {

            toppings.put(topping, currentQuantity - quantity);

        }
        if (toppings.get(topping) == 0) {
            toppings.remove(topping);
        }


    }

    void removeCream(Cream cream, int quantity) {

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        int currentQuantity = creams.getOrDefault(cream, 0);

        if (currentQuantity >= quantity) {

            creams.put(cream, currentQuantity - quantity);

        }
        if (creams.get(cream) == 0) {

            creams.remove(cream);

        }

    }
}
