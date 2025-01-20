package org.cupcakes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCupcake {
    private IngredientStorage storage;
    private Prices price;

    @BeforeEach
    public void setUp() {
        storage = new IngredientStorage();
        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addCream(Cream.RASPBERRY, 5);
        storage.addTopping(Topping.CHOCOLATECHIPS, 20);
        storage.addTopping(Topping.COOKIEDOUGHT, 20);

        Map<Cream, Integer> creamPrices = Map.ofEntries(
                Map.entry(Cream.RASPBERRY, 1)
        );

        Map<CupcakeBase, Integer> basePrices = Map.ofEntries(
                Map.entry(CupcakeBase.VANILLABASE, 1)
        );

        Map<Topping, Integer> toppingPrices = Map.ofEntries(
                Map.entry(Topping.CHOCOLATECHIPS, 1),
                Map.entry(Topping.COOKIEDOUGHT, 1)
        );
        Map<String, Integer> dailyCupcake = new HashMap<>();
        dailyCupcake.put("test", 4);

        price = new Prices(creamPrices, basePrices, toppingPrices, dailyCupcake);
    }

    @Test
    public void TestOneToppingCupcake() {
        Cupcake cup = new Cupcake(CupcakeBase.VANILLABASE, Cream.RASPBERRY, List.of(Topping.CHOCOLATECHIPS));
        Assertions.assertEquals(3, cup.getPrice(price));
    }

    @Test
    public void TestTwoToppingCupcake() {
        Cupcake cup = new Cupcake(CupcakeBase.VANILLABASE, Cream.RASPBERRY, List.of(Topping.CHOCOLATECHIPS, Topping.COOKIEDOUGHT));
        Assertions.assertEquals(3, cup.getPrice(price));
    }

    @Test
    public void TestMoreThanTwoToppingCupcake() {
        Assertions.assertThrows(Exception.class, ()->new Cupcake(CupcakeBase.VANILLABASE, Cream.RASPBERRY, List.of(Topping.CHOCOLATECHIPS, Topping.MARSHMALLOWS, Topping.VERMICELLI)));
    }
}
