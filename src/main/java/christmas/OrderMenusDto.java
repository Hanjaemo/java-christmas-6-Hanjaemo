package christmas;

import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMenusDto {

    private final Map<String, Integer> menus;

    private OrderMenusDto(OrderMenus orderMenus) {
        this.menus = orderMenus.getOrderMenus()
                .stream()
                .collect(Collectors.toMap(
                        orderMenu -> orderMenu.getMenu().getName(),
                        OrderMenu::getQuantity));
    }

    public static OrderMenusDto from(OrderMenus orderMenus) {
        return new OrderMenusDto(orderMenus);
    }

    public Map<String, Integer> getMenus() {
        return Map.copyOf(menus);
    }
}
