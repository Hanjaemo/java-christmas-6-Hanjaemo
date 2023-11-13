package christmas.domain.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {

    private final List<OrderMenu> orderMenus;

    public Order(List<OrderMenu> orderMenus) {
        validateTotalQuantity(orderMenus);
        validateDuplicated(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void validateTotalQuantity(List<OrderMenu> orderMenus) {
        int totalQuantity = orderMenus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum();
        if (totalQuantity < 1 || totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplicated(List<OrderMenu> orderMenus) {
        Set<OrderMenu> distinctOrderMenus = new HashSet<>(orderMenus);
        if (distinctOrderMenus.size() != orderMenus.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int countMainMenus() {
        return orderMenus.stream()
                .filter(OrderMenu::isMenuMain)
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }

    public int countDessertMenus() {
        return orderMenus.stream()
                .filter(OrderMenu::isMenuDessert)
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }

    public int calculateTotalOrderAmount() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::calculatePrice)
                .sum();
    }

    public void addOrderMenu(OrderMenu orderMenu) {
        orderMenus.add(orderMenu);
    }

    public List<OrderMenu> getOrderMenus() {
        return List.copyOf(orderMenus);
    }
}
