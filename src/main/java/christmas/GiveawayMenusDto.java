package christmas;

import java.util.Map;

public class GiveawayMenusDto {

    private final Map<String, Integer> menus;

    public GiveawayMenusDto(Map<String, Integer> menus) {
        this.menus = menus;
    }

    public Map<String, Integer> getMenus() {
        return Map.copyOf(menus);
    }
}
