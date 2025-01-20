package org.cupcakes;

import java.util.Collection;
import java.util.List;

public record CupcakeMenu(List<MenuEntry<Cream>> creams, List<MenuEntry<CupcakeBase>> bases,
                          List<MenuEntry<Topping>> toppings,
                          List<MenuEntry<String>> dailyCupcakes) {
}
