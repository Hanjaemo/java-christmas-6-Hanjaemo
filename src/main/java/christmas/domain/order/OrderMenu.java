package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.error.ErrorMessage;
import java.util.Objects;

public class OrderMenu {

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 20;

    private final Menu menu;
    private final int quantity;

    public OrderMenu(Menu menu, int quantity) {
        this.menu = menu;
        if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        this.quantity = quantity;
    }

    public boolean isMenuMain() {
        return menu.isMain();
    }

    public boolean isMenuDessert() {
        return menu.isDessert();
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity;
    }

    public boolean isNotBeverage() {
        return menu.isNotBeverage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMenu orderMenu = (OrderMenu) o;
        return menu == orderMenu.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
