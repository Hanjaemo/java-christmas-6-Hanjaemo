package christmas.dto;

import christmas.domain.menu.Menu;
import java.util.Map;
import java.util.stream.Collectors;

public class GiveawayMenusDto {

    private final Map<String, Integer> menus;

    private GiveawayMenusDto(Map<Menu, Integer> giveawayMenus) {
        this.menus = giveawayMenus.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        giveawayMenu -> giveawayMenu.getKey().getName(),
                        giveawayMenu -> giveawayMenu.getValue()
                ));
    }

    public static GiveawayMenusDto from(Map<Menu, Integer> giveawayMenus) {
        return new GiveawayMenusDto(giveawayMenus);
    }

    public Map<String, Integer> getMenus() {
        return Map.copyOf(menus);
    }
}
