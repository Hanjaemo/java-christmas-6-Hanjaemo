package christmas;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import christmas.domain.order.OrderMenu;

public class OrderMenusDto {

    private final Map<String, Integer> menus;

    public OrderMenusDto(List<OrderMenu> orderMenus) {
        this.menus = orderMenus.stream()
                .collect(Collectors.toMap(
                        orderMenu -> orderMenu.getMenu().getName(),
                        OrderMenu::getQuantity
                ));
    }

    public Map<String, Integer> getMenus() {
        return Map.copyOf(menus);
    }
}
