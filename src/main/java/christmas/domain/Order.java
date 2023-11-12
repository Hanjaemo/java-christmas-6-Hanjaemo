package christmas.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {

    private final Map<Menu, Integer> menus;

    public Order(Map<Menu, Integer> menus) {
        validateEachMenuQuantity(menus);
        validateTotalMenuQuantity(menus);
        validateDuplicated(menus);
        this.menus = menus;
    }

    private void validateEachMenuQuantity(Map<Menu, Integer> menus) {
        for (Integer quantity : menus.values()) {
            if (quantity < 1 || quantity > 20) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateTotalMenuQuantity(Map<Menu, Integer> menus) {
        int totalMenuQuantity = menus.values().stream()
                .mapToInt(quantity -> quantity)
                .sum();
        if (totalMenuQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplicated(Map<Menu, Integer> menus) {

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

    public void addMenu(Menu menu) {
        menus.put(menu, menus.getOrDefault(menu, 0) + 1);
    }

    public Map<String, Integer> getOrderMenus() {
        return menus.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        menu -> menu.getKey().getName(),
                        menu -> menu.getValue()));
    }
}
