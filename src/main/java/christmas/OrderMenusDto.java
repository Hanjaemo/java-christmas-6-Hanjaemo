package christmas;

import java.util.Map;

public class OrderMenusDto {

    private final Map<String, Integer> menus;

    public OrderMenusDto(Map<String, Integer> menus) {
        this.menus = menus;
    }

    public Map<String, Integer> getMenus() {
        return Map.copyOf(menus);
    }
}
