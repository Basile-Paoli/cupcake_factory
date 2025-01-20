package org.cupcakes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TestIngredientStorage {
    private IngredientStorage storage;

    @BeforeEach
    public void setUp() {
        storage = new IngredientStorage();
    }

    // --------------------------------------------------------------------------- //

    @Test
    public void testAddBaseNormal() {
        storage.addBase(CupcakeBase.VANILLABASE, 12);

        Map<CupcakeBase, Integer> bases = storage.getBases();

        Assertions.assertTrue(bases.containsKey(CupcakeBase.VANILLABASE),
                "Le stockage devrait contenir au moins une base à la vanille");
        Assertions.assertEquals(12, bases.get(CupcakeBase.VANILLABASE),
                "Il devrait y avoir 12 bases vanille");
    }

    @Test
    public void testAddBaseNegative() {
        Assertions.assertThrows(Exception.class, () -> storage.addBase(CupcakeBase.CHOCOLATE, -1));
    }

    // --------------------------------------------------------------------------- //

    @Test
    public void testAddToppingNormal() {
        storage.addTopping(Topping.CHOCOLATECHIPS, 12);

        Map<Topping, Integer> toppings = storage.getToppings();

        Assertions.assertTrue(toppings.containsKey(Topping.CHOCOLATECHIPS),
                "Le stockage de topping devrait contenir au moins un topping au éclat de chocolat");
        Assertions.assertEquals(12, toppings.get(Topping.CHOCOLATECHIPS),
                "Il devrait y avoir 12 topping au éclats de chocolat");
    }

    @Test
    public void testAddToppingNegative() {
        Assertions.assertThrows(Exception.class, () -> storage.addTopping(Topping.COOKIEDOUGHT, -12));
    }

    // --------------------------------------------------------------------------- //

    @Test
    public void testAddCream() {
        storage.addCream(Cream.CHOCOLATE, 12);

        Map<Cream, Integer> creams = storage.getCreams();

        Assertions.assertTrue(creams.containsKey(Cream.CHOCOLATE),
                "Le stockage devrait contenir au moins une crème au chocolat");
        Assertions.assertEquals(12, creams.get(Cream.CHOCOLATE),
                "Il devrait y avoir 12 crème au chocolat");
    }

    @Test
    public void testAddCreamNegative() {
        Assertions.assertThrows(Exception.class, () -> storage.addCream(Cream.VANILLACREAM, -12));
    }


    // --------------------------------------------------------------------------- //
    @Test
    public void testRemoveNormal() {
        storage.addBase(CupcakeBase.VANILLABASE, 1);
        storage.removeBase(CupcakeBase.VANILLABASE, 1);
        var hashmapBase = storage.getBases();
        Assertions.assertFalse(hashmapBase.containsKey(CupcakeBase.VANILLABASE),
                "La base vanilla ne devrait plus exister dans le stockage");
    }

    @Test
    public void testRemoveNormalButStillHaveInDB() {
        storage.addBase(CupcakeBase.VANILLABASE, 12);
        storage.removeBase(CupcakeBase.VANILLABASE, 5);

        Map<CupcakeBase, Integer> base = storage.getBases();
        Assertions.assertTrue(base.containsKey(CupcakeBase.VANILLABASE),
                "La base vanilla devrait exister dans le stockage");

        Assertions.assertEquals(7, base.get(CupcakeBase.VANILLABASE),
                "Il devrait rester seulement 7 base vanille dans le stockage");
    }

    @Test
    public void testRemoveNormalButRemoveMoreThanHaveInDB() {
        storage.addBase(CupcakeBase.VANILLABASE, 2);
        Assertions.assertThrows(Exception.class, () -> storage.removeBase(CupcakeBase.VANILLABASE, 5));
    }

    @Test
    public void testRemoveBaseNegative() {
        storage.addBase(CupcakeBase.VANILLABASE, 1);
        Assertions.assertThrows(Exception.class, () -> storage.removeBase(CupcakeBase.VANILLABASE, -1));
    }

    @Test
    public void testRemoveBaseDontExist() {
        Assertions.assertThrows(Exception.class, ()->storage.removeBase(CupcakeBase.VANILLABASE, 1));
    }

    // --------------------------------------------------------------------------- //

    @Test
    public void testRemoveToppingNormal() {
        storage.addTopping(Topping.CHOCOLATECHIPS, 1);
        storage.removeTopping(Topping.CHOCOLATECHIPS, 1);
        var hashmapTopping = storage.getToppings();
        Assertions.assertFalse(hashmapTopping.containsKey(Topping.CHOCOLATECHIPS),
                "Le topping Chocolate Chips ne devrait plus exister dans le stockage");
    }

    @Test
    public void testRemoveToppingNormalButStillHaveInDB() {
        storage.addTopping(Topping.CHOCOLATECHIPS, 10);
        storage.removeTopping(Topping.CHOCOLATECHIPS, 4);

        Map<Topping, Integer> toppings = storage.getToppings();
        Assertions.assertTrue(toppings.containsKey(Topping.CHOCOLATECHIPS),
                "Le topping Chocolate Chips devrait toujours exister dans le stockage");
        Assertions.assertEquals(6, toppings.get(Topping.CHOCOLATECHIPS),
                "Il devrait rester seulement 6 Chocolate Chips dans le stockage");
    }

    @Test
    public void testRemoveToppingNormalButRemoveMoreThanHaveInDB() {
        storage.addTopping(Topping.CHOCOLATECHIPS, 2);
        Assertions.assertThrows(Exception.class, () -> storage.removeTopping(Topping.CHOCOLATECHIPS, 5));
    }

    @Test
    public void testRemoveToppingNegative() {
        storage.addTopping(Topping.CHOCOLATECHIPS, 1);
        Assertions.assertThrows(Exception.class, () -> storage.removeTopping(Topping.CHOCOLATECHIPS, -1));
    }

    @Test
    public void testRemoveToppingDontExist() {
        Assertions.assertThrows(Exception.class, () -> storage.removeTopping(Topping.CHOCOLATECHIPS, 1));
    }

    // --------------------------------------------------------------------------- //

    @Test
    public void testRemoveCreamNormal() {
        storage.addCream(Cream.RASPBERRY, 1);
        storage.removeCream(Cream.RASPBERRY, 1);
        var hashmapCream = storage.getCreams();
        Assertions.assertFalse(hashmapCream.containsKey(Cream.RASPBERRY),
                "La crème Buttercream ne devrait plus exister dans le stockage");
    }

    @Test
    public void testRemoveCreamNormalButStillHaveInDB() {
        storage.addCream(Cream.RASPBERRY, 8);
        storage.removeCream(Cream.RASPBERRY, 3);

        Map<Cream, Integer> creams = storage.getCreams();
        Assertions.assertTrue(creams.containsKey(Cream.RASPBERRY),
                "La crème Buttercream devrait toujours exister dans le stockage");
        Assertions.assertEquals(5, creams.get(Cream.RASPBERRY),
                "Il devrait rester seulement 5 Buttercream dans le stockage");
    }

    @Test
    public void testRemoveCreamNormalButRemoveMoreThanHaveInDB() {
        storage.addCream(Cream.RASPBERRY, 2);
        Assertions.assertThrows(Exception.class, () -> storage.removeCream(Cream.RASPBERRY, 5));
    }

    @Test
    public void testRemoveCreamNegative() {
        storage.addCream(Cream.RASPBERRY, 1);
        Assertions.assertThrows(Exception.class, () -> storage.removeCream(Cream.RASPBERRY, -1));
    }

    @Test
    public void testRemoveCreamDontExist() {
        Assertions.assertThrows(Exception.class, () -> storage.removeCream(Cream.RASPBERRY, 1));
    }

}
