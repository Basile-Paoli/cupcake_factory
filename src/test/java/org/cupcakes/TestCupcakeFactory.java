package org.cupcakes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCupcakeFactory {

    private IngredientStorage storage;
    private CupcakeFactory factory;
    private Prices prices;

    @BeforeEach
    public void setUp() {
        storage = new IngredientStorage();
    }

    @Test
    public void TestGetMenu() {

        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 20);
        storage.addCream(Cream.RASPBERRY, 5);

        prices = new Prices(
                Map.of(Cream.RASPBERRY, 1),
                Map.of(CupcakeBase.VANILLABASE, 1),
                Map.of(Topping.CHOCOLATECHIPS, 1),
                Map.of("test", 4)
        );

        factory = new CupcakeFactory(storage, prices);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertTrue(menu.creams().contains(new MenuEntry<>(Cream.RASPBERRY, 1)));
        Assertions.assertTrue(menu.bases().contains(new MenuEntry<>(CupcakeBase.VANILLABASE, 1)));
        Assertions.assertTrue(menu.toppings().contains(new MenuEntry<>(Topping.CHOCOLATECHIPS, 1)));
        Assertions.assertTrue(menu.dailyCupcakes().contains(new MenuEntry<>("test", 4)));
    }

    @Test
    public void TestEmptyItem() {

        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 20);
        storage.addCream(Cream.RASPBERRY, 4);
        storage.addCream(Cream.CUCUMBER, 0);

        prices = new Prices(
                Map.ofEntries(
                        Map.entry(Cream.RASPBERRY, 1),
                        Map.entry(Cream.CUCUMBER, 1)
                ),
                Map.of(CupcakeBase.VANILLABASE, 1),
                Map.of(Topping.CHOCOLATECHIPS, 1),
                new HashMap<>());

        factory = new CupcakeFactory(storage, prices);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertFalse(menu.creams().stream().anyMatch(e -> e.item().equals(Cream.CUCUMBER)));
    }

    @Test
    public void TestNoCream() {

        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 20);
        storage.addCream(Cream.RASPBERRY, 0);

        prices = new Prices(
                Map.of(Cream.RASPBERRY, 1),
                Map.of(CupcakeBase.VANILLABASE, 1),
                Map.of(Topping.CHOCOLATECHIPS, 1),
                new HashMap<>()
        );

        factory = new CupcakeFactory(storage, prices);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertFalse(menu.creams().contains(new MenuEntry<>(Cream.RASPBERRY, 1)));
        Assertions.assertFalse(menu.bases().contains(new MenuEntry<>(CupcakeBase.VANILLABASE, 1)));
        Assertions.assertFalse(menu.toppings().contains(new MenuEntry<>(Topping.CHOCOLATECHIPS, 1)));
    }

    @Test
    public void TestNoTopping() {

        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.addTopping(Topping.CHOCOLATECHIPS, 0);
        storage.addCream(Cream.RASPBERRY, 2);

        Map<String, Integer> dailyCupcake = new HashMap<>();

        prices = new Prices(
                Map.of(Cream.RASPBERRY, 1),
                Map.of(CupcakeBase.VANILLABASE, 1),
                Map.of(Topping.CHOCOLATECHIPS, 1),
                dailyCupcake
        );

        factory = new CupcakeFactory(storage, prices);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertFalse(menu.creams().contains(new MenuEntry<>(Cream.RASPBERRY, 1)));
        Assertions.assertFalse(menu.bases().contains(new MenuEntry<>(CupcakeBase.VANILLABASE, 1)));
        Assertions.assertFalse(menu.toppings().contains(new MenuEntry<>(Topping.CHOCOLATECHIPS, 1)));
    }

    @Test
    public void TestNoBase() {

        storage.addBase(CupcakeBase.VANILLABASE, 0);
        storage.addTopping(Topping.CHOCOLATECHIPS, 10);
        storage.addCream(Cream.RASPBERRY, 2);

        prices = new Prices(
                Map.of(Cream.RASPBERRY, 1),
                Map.of(CupcakeBase.VANILLABASE, 1),
                Map.of(Topping.CHOCOLATECHIPS, 1),
                new HashMap<>()
        );

        factory = new CupcakeFactory(storage, prices);

        CupcakeMenu menu = factory.getMenu();

        Assertions.assertFalse(menu.creams().contains(new MenuEntry<>(Cream.RASPBERRY, 1)));
        Assertions.assertFalse(menu.bases().contains(new MenuEntry<>(CupcakeBase.VANILLABASE, 1)));
        Assertions.assertFalse(menu.toppings().contains(new MenuEntry<>(Topping.CHOCOLATECHIPS, 1)));
    }


    @Test
    public void TestTotalRevenue() {
        storage.addBase(CupcakeBase.VANILLABASE, 3);
        storage.addTopping(Topping.CHOCOLATECHIPS, 10);
        storage.addCream(Cream.RASPBERRY, 4);

        prices = new Prices(
                Map.of(Cream.RASPBERRY, 4),
                Map.of(CupcakeBase.VANILLABASE, 2),
                Map.of(Topping.CHOCOLATECHIPS, 5),
                new HashMap<>()
        );
        var cupcake = new Cupcake(CupcakeBase.VANILLABASE, Cream.RASPBERRY, List.of(Topping.CHOCOLATECHIPS));

        var price = cupcake.getPrice(prices);

        factory = new CupcakeFactory(storage, prices);

        Order order = new Order(List.of(cupcake, cupcake, cupcake), List.of());

        factory.orderCupcakes(order);

        Assertions.assertEquals(price * 3, factory.getTotalRevenue());
    }
}
