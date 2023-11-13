package christmas.service;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    public OrderMenus order(List<String> orderMenus) {
        return new OrderMenus(convertToOrderMenuList(orderMenus));
    }

    private List<OrderMenu> convertToOrderMenuList(List<String> orderMenus) {
        return orderMenus.stream()
                .map(orderMenu -> orderMenu.split("-"))
                .map(orderMenu -> new OrderMenu(Menu.from(orderMenu[0]), Integer.parseInt(orderMenu[1])))
                .collect(Collectors.toList());
    }

    public int calculateTotalOrderAmount(OrderMenus orderMenus) {
        return orderMenus.calculateTotalOrderAmount();
    }
}
