package christmas;

import java.util.Map;
import java.util.stream.Collectors;

import christmas.domain.menu.Menu;

public class GiveawayMenusDto {

    private final Map<String, Integer> menus;

    public GiveawayMenusDto(Map<Menu, Integer> menus) {
        this.menus = menus.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        menu -> menu.getKey().getName(),
                        menu -> menu.getValue()
                ));
    }

    public Map<String, Integer> getMenus() {
        return Map.copyOf(menus);
    }
}
