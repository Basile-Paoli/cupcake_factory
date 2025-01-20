package org.cupcakes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestCupcakeFactory {

    private IngredientStorage storage;
    private CupcakeFactory factory;
    private Prices price;

    @BeforeEach
    public void setUp() {
        storage = new IngredientStorage();
    }

    @Test
    public void TestGetMenu(){

        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 20);
        storage.addCream(Cream.RASPBERRY, 5);

        Map<Cream, Integer> creamPrices = Map.ofEntries(
                Map.entry(Cream.RASPBERRY, 1)
        );

        Map<CupcakeBase, Integer> basePrices = Map.ofEntries(
                Map.entry(CupcakeBase.VANILLABASE, 1)
        );

        Map<Topping, Integer> toppingPrices = Map.ofEntries(
                Map.entry(Topping.CHOCOLATECHIPS, 1)
        );
        Map<String, Integer> dailyCupcake = new HashMap<>();
        dailyCupcake.put("test", 4);

        price = new Prices(creamPrices, basePrices, toppingPrices, dailyCupcake);

        factory = new CupcakeFactory(storage, price);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertTrue(menu.creams().contains(new MenuEntry<>(Cream.RASPBERRY, 1)));
        Assertions.assertTrue(menu.bases().contains(new MenuEntry<>(CupcakeBase.VANILLABASE, 1)));
        Assertions.assertTrue(menu.toppings().contains(new MenuEntry<>(Topping.CHOCOLATECHIPS, 1)));
        Assertions.assertTrue(menu.dailyCupcakes().contains(new MenuEntry<>("test", 4)));
    }

    @Test
    public void TestNoCream(){

        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 20);
        storage.addCream(Cream.RASPBERRY, 0);

        Map<Cream, Integer> creamPrices = Map.ofEntries(
                Map.entry(Cream.RASPBERRY, 1)
        );

        Map<CupcakeBase, Integer> basePrices = Map.ofEntries(
                Map.entry(CupcakeBase.VANILLABASE, 1)
        );

        Map<Topping, Integer> toppingPrices = Map.ofEntries(
                Map.entry(Topping.CHOCOLATECHIPS, 1)
        );
        Map<String, Integer> dailyCupcake = new HashMap<>();

        price = new Prices(creamPrices, basePrices, toppingPrices, dailyCupcake);

        factory = new CupcakeFactory(storage, price);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertFalse(menu.creams().contains(new MenuEntry<>(Cream.RASPBERRY, 1)));
        Assertions.assertFalse(menu.bases().contains(new MenuEntry<>(CupcakeBase.VANILLABASE, 1)));
        Assertions.assertFalse(menu.toppings().contains(new MenuEntry<>(Topping.CHOCOLATECHIPS, 1)));

        //Assertions.assertTrue(menu.dailyCupcakes().contains(new MenuEntry<>(Cream.CHOCOLATE, 1)));

    }

    @Test
    public void TestNoTopping(){

        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 0);
        storage.addCream(Cream.RASPBERRY, 2);

        Map<Cream, Integer> creamPrices = Map.ofEntries(
                Map.entry(Cream.RASPBERRY, 1)
        );

        Map<CupcakeBase, Integer> basePrices = Map.ofEntries(
                Map.entry(CupcakeBase.VANILLABASE, 1)
        );

        Map<Topping, Integer> toppingPrices = Map.ofEntries(
                Map.entry(Topping.CHOCOLATECHIPS, 1)
        );
        Map<String, Integer> dailyCupcake = new HashMap<>();

        price = new Prices(creamPrices, basePrices, toppingPrices, dailyCupcake);

        factory = new CupcakeFactory(storage, price);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertFalse(menu.creams().contains(new MenuEntry<>(Cream.RASPBERRY, 1)));
        Assertions.assertFalse(menu.bases().contains(new MenuEntry<>(CupcakeBase.VANILLABASE, 1)));
        Assertions.assertFalse(menu.toppings().contains(new MenuEntry<>(Topping.CHOCOLATECHIPS, 1)));

        //Assertions.assertTrue(menu.dailyCupcakes().contains(new MenuEntry<>(Cream.CHOCOLATE, 1)));

    }

    @Test
    public void TestNoBase(){

        storage.addBase(CupcakeBase.VANILLABASE, 0);
        storage.addTopping(Topping.CHOCOLATECHIPS, 10);
        storage.addCream(Cream.RASPBERRY, 2);

        Map<Cream, Integer> creamPrices = Map.ofEntries(
                Map.entry(Cream.RASPBERRY, 1)
        );

        Map<CupcakeBase, Integer> basePrices = Map.ofEntries(
                Map.entry(CupcakeBase.VANILLABASE, 1)
        );

        Map<Topping, Integer> toppingPrices = Map.ofEntries(
                Map.entry(Topping.CHOCOLATECHIPS, 1)
        );
        Map<String, Integer> dailyCupcake = new HashMap<>();

        price = new Prices(creamPrices, basePrices, toppingPrices, dailyCupcake);

        factory = new CupcakeFactory(storage, price);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertFalse(menu.creams().contains(new MenuEntry<>(Cream.RASPBERRY, 1)));
        Assertions.assertFalse(menu.bases().contains(new MenuEntry<>(CupcakeBase.VANILLABASE, 1)));
        Assertions.assertFalse(menu.toppings().contains(new MenuEntry<>(Topping.CHOCOLATECHIPS, 1)));

        //Assertions.assertTrue(menu.dailyCupcakes().contains(new MenuEntry<>(Cream.CHOCOLATE, 1)));

    }
}
