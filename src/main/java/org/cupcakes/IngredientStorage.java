package org.cupcakes;

import java.util.HashMap;

public class IngredientStorage {

    private HashMap<CupcakeBase, Integer> bases = new HashMap<>();
    private HashMap<Topping, Integer> toppings = new HashMap<>();
    private HashMap<Cream, Integer> creams = new HashMap<>();
    private HashMap<String, Integer> cupcakesDay = new HashMap<>();

    HashMap<CupcakeBase, Integer> getBases() {
        return bases;
    }

    HashMap<Topping, Integer> getToppings() {
        return toppings;
    }

    HashMap<Cream, Integer> getCreams() {
        return creams;
    }

    HashMap<String, Integer> getCupcakesDay() {
        return cupcakesDay;
    }

    private void assertPositive(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }

    private void assertSuperior(int currentQuantity, int quantity) {
        if (currentQuantity < quantity) {
            throw new IllegalArgumentException("Insufficient quantity");
        }
    }

    void addBase(CupcakeBase base, int quantity) {
        assertPositive(quantity);
        bases.put(base, bases.getOrDefault(base, 0) + quantity);
    }

    void addTopping(Topping topping, int quantity) {
        assertPositive(quantity);
        toppings.put(topping, toppings.getOrDefault(topping, 0) + quantity);
    }

    void addCream(Cream cream, int quantity) {
        assertPositive(quantity);
        creams.put(cream, creams.getOrDefault(cream, 0) + quantity);
    }

    void addCupcakeDay(String cupcakeName, int quantity) {
        assertPositive(quantity);
        cupcakesDay.put(cupcakeName, cupcakesDay.getOrDefault(cupcakeName, 0) + quantity);
    }

    void removeBase(CupcakeBase base, int quantity) {
        assertPositive(quantity);
        int currentQuantity = bases.getOrDefault(base, 0);
        assertSuperior(currentQuantity, quantity);

        if (currentQuantity >= quantity) {
            bases.put(base, currentQuantity - quantity);
        }
        if (bases.get(base) == 0) {
            bases.remove(base);
        }
    }

    void removeTopping(Topping topping, int quantity) {
        assertPositive(quantity);
        int currentQuantity = toppings.getOrDefault(topping, 0);
        assertSuperior(currentQuantity, quantity);
        if (currentQuantity >= quantity) {
            toppings.put(topping, currentQuantity - quantity);
        }
        if (toppings.get(topping) == 0) {
            toppings.remove(topping);
        }
    }

    void removeCream(Cream cream, int quantity) {
        assertPositive(quantity);
        int currentQuantity = creams.getOrDefault(cream, 0);
        assertSuperior(currentQuantity, quantity);

        if (currentQuantity >= quantity) {
            creams.put(cream, currentQuantity - quantity);
        }
        if (creams.get(cream) == 0) {
            creams.remove(cream);
        }

    }

    void removeCupcakeDay(String cupcakeName, int quantity) {
        assertPositive(quantity);
        if (cupcakesDay.containsKey(cupcakeName)) {
            int currentQuantity = cupcakesDay.get(cupcakeName);

            assertSuperior(currentQuantity, quantity);
            if (quantity > 0 && quantity <= currentQuantity) {
                int newQuantity = currentQuantity - quantity;
                if (newQuantity > 0) {
                    cupcakesDay.put(cupcakeName, newQuantity);
                } else {
                    cupcakesDay.remove(cupcakeName);
                }
            }
        }
    }

}
