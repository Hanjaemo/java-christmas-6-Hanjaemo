package christmas.domain;

import java.util.List;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> menus;

    public Order(Map<Menu, Integer> menus) {
        this.menus = menus;
    }

    public int countMainMenus() {
        List<Menu> mainMenus = menus.keySet()
                .stream()
                .filter(Menu::isMain)
                .toList();
        return mainMenus.stream()
                .mapToInt(menus::get)
                .sum();
    }

    public int countDessertMenus() {
        List<Menu> mainMenus = menus.keySet()
                .stream()
                .filter(Menu::isDessert)
                .toList();
        return mainMenus.stream()
                .mapToInt(menus::get)
                .sum();
    }

    public int calculateTotalOrderAmount() {
        int totalOrderAmount = 0;
        for (Menu menu : menus.keySet()) {
            totalOrderAmount += (menu.getPrice() * menus.get(menu));
        }
        return totalOrderAmount;
    }
}
