package org.cupcakes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestPrices {

    private IngredientStorage storage;
    private CupcakeFactory factory;
    private Prices price;

    @BeforeEach
    public void setUp() {
        storage = new IngredientStorage();
    }

    @Test
    public void TestPriceNormal(){
        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 20);
        storage.addCream(Cream.RASPBERRY, 0);

        Map<Cream, Integer> creamPrices = Map.ofEntries(
                Map.entry(Cream.RASPBERRY, 1)
        );

        Map<CupcakeBase, Integer> basePrices = Map.ofEntries(
                Map.entry(CupcakeBase.VANILLABASE, 2)
        );

        Map<Topping, Integer> toppingPrices = Map.ofEntries(
                Map.entry(Topping.CHOCOLATECHIPS, 3)
        );
        Map<String, Integer> dailyCupcake = new HashMap<>();
        dailyCupcake.put("test", 4);

        price = new Prices(creamPrices, basePrices, toppingPrices, dailyCupcake);

        Assertions.assertEquals(2,price.getBasePrice(CupcakeBase.VANILLABASE), "Le prix de la base n'est pas le bon");
        Assertions.assertEquals(1,price.getCreamPrice(Cream.RASPBERRY), "Le prix n'est pas le bon");
        Assertions.assertEquals(3,price.getToppingPrice(Topping.CHOCOLATECHIPS), "Le prix n'est pas le bon");
        Assertions.assertEquals(4,price.getDailyCupcakePrice("test"), "Le prix n'est pas le bon");
    }

    @Test
    public void TestPriceNegative(){
        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 20);
        storage.addCream(Cream.RASPBERRY, 0);

        Map<Cream, Integer> creamPrices = Map.ofEntries(
                Map.entry(Cream.RASPBERRY, -1)
        );

        Map<CupcakeBase, Integer> basePrices = Map.ofEntries(
                Map.entry(CupcakeBase.VANILLABASE, 2)
        );

        Map<Topping, Integer> toppingPrices = Map.ofEntries(
                Map.entry(Topping.CHOCOLATECHIPS, 3)
        );
        Map<String, Integer> dailyCupcake = new HashMap<>();
        dailyCupcake.put("test", 4);

        Assertions.assertThrows(Exception.class, ()->new Prices(creamPrices, basePrices, toppingPrices, dailyCupcake));
    }
}
