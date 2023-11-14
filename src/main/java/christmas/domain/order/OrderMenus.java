package christmas.domain.order;

import christmas.error.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderMenus {

    private static final int MIN_TOTAL_QUANTITY = 1;
    private static final int MAX_TOTAL_QUANTITY = 20;

    private final List<OrderMenu> orderMenus;

    public OrderMenus(List<OrderMenu> orderMenus) {
        validateTotalQuantity(orderMenus);
        validateDuplicated(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void validateTotalQuantity(List<OrderMenu> orderMenus) {
        int totalQuantity = orderMenus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum();
        if (totalQuantity < MIN_TOTAL_QUANTITY || totalQuantity > MAX_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateDuplicated(List<OrderMenu> orderMenus) {
        Set<OrderMenu> distinctOrderMenus = new HashSet<>(orderMenus);
        if (distinctOrderMenus.size() != orderMenus.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public int calculateTotalOrderAmount() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::calculatePrice)
                .sum();
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

    public void addOrderMenu(OrderMenu orderMenu) {
        orderMenus.add(orderMenu);
    }

    public List<OrderMenu> getOrderMenus() {
        return List.copyOf(orderMenus);
    }
}
