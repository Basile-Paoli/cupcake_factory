package org.cupcakes;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOrder {


    private IngredientStorage storage;
    private CupcakeFactory factory;
    private Prices price;


    @BeforeEach
    public void setUp() {

        Map<Cream, Integer> creamPrices = Map.ofEntries(
                Map.entry(Cream.RASPBERRY, 1),
                Map.entry(Cream.CUCUMBER, 4)
        );

        Map<CupcakeBase, Integer> basePrices = Map.ofEntries(
                Map.entry(CupcakeBase.VANILLABASE, 1),
                Map.entry(CupcakeBase.CHOCOLATE, 2)
        );

        Map<Topping, Integer> toppingPrices = Map.ofEntries(
                Map.entry(Topping.CHOCOLATECHIPS, 1),
                Map.entry(Topping.VERMICELLI,3)
        );
        Map<String, Integer> dailyCupcake = new HashMap<>();
        dailyCupcake.put("test", 1);

        price = new Prices(creamPrices, basePrices, toppingPrices, dailyCupcake);


    }

    @Test
    public void testOrderNormal2Toppings(){
        Cupcake test = new Cupcake(CupcakeBase.VANILLABASE, Cream.RASPBERRY, List.of(Topping.CHOCOLATECHIPS, Topping.VERMICELLI));
        var order = new Order(List.of(test),List.of());
        int expectedPrice = 5;
        Assertions.assertEquals(expectedPrice, order.getPrice(price));
    }

    @Test
    public void testOrderNormal(){
        Cupcake test = new Cupcake(CupcakeBase.VANILLABASE, Cream.RASPBERRY, List.of(Topping.CHOCOLATECHIPS));
        var order = new Order(List.of(test),List.of());
        int expectedPrice = 3;
        Assertions.assertEquals(expectedPrice, order.getPrice(price));
    }

    @Test
    public void testOrderNormal6Cupcake(){
        Cupcake test = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS)); //6
        Cupcake test1 = new Cupcake(CupcakeBase.CHOCOLATE, Cream.RASPBERRY, List.of(Topping.VERMICELLI)); //6
        Cupcake test2 = new Cupcake(CupcakeBase.VANILLABASE, Cream.RASPBERRY, List.of(Topping.CHOCOLATECHIPS));//3
        Cupcake test3 = new Cupcake(CupcakeBase.CHOCOLATE, Cream.CUCUMBER, List.of(Topping.VERMICELLI));//8
        Cupcake test4 = new Cupcake(CupcakeBase.VANILLABASE, Cream.RASPBERRY, List.of(Topping.CHOCOLATECHIPS));//3
        Cupcake test5 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS, Topping.VERMICELLI));//9

        var order = new Order(List.of(test,test1,test2,test3,test4,test5),List.of());
        int expectedPrice = 32;
        Assertions.assertEquals(expectedPrice, order.getPrice(price));
    }

    @Test
    public void testOrderNormal6CupcakeSamePrice(){
        Cupcake test = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test1 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test2 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test3 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test4 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test5 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));

        var order = new Order(List.of(test,test1,test2,test3,test4,test5),List.of());
        int expectedPrice = 30;
        Assertions.assertEquals(expectedPrice, order.getPrice(price));
    }

    @Test
    public void testOrderNormal6CupcakeAndOneDaily(){
        Cupcake test = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test1 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test2 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test3 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test4 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test5 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));

        var order = new Order(List.of(test,test1,test2,test3,test4,test5),List.of("test"));
        int expectedPrice = 31;
        Assertions.assertEquals(expectedPrice, order.getPrice(price));
    }

    @Test
    public void testOrderNormal5CupcakeAndOneDaily(){
        Cupcake test = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test1 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test2 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test3 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));
        Cupcake test4 = new Cupcake(CupcakeBase.VANILLABASE, Cream.CUCUMBER, List.of(Topping.CHOCOLATECHIPS));


        var order = new Order(List.of(test,test1,test2,test3,test4),List.of("test"));
        int expectedPrice = 31;
        Assertions.assertEquals(expectedPrice, order.getPrice(price));
    }

}
