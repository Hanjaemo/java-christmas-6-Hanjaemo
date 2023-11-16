package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.error.ErrorMessage;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {

    @DisplayName("주문한 전체 메뉴의 수량이 최솟값보다 작은 경우 예외가 발생한다.")
    @Test
    void createOrderMenus_Fail_ByTotalQuantityIsLessThanMinimum() {
        // when, then
        Assertions.assertThatThrownBy(() -> new OrderMenus(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문한 전체 메뉴의 수량이 최댓값보다 큰 경우 예외가 발생한다.")
    @Test
    void createOrderMenus_Fail_ByTotalQuantityIsMoreThanMaximum() {
        // given
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu(Menu.CHAMPAGNE, 8),
                new OrderMenu(Menu.TAPAS, 6),
                new OrderMenu(Menu.T_BONE_STEAK, 10)
        );

        // when, then
        Assertions.assertThatThrownBy(() -> new OrderMenus(orderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문한 전체 메뉴 중에서 중복되는 메뉴가 있는 경우 예외가 발생한다.")
    @Test
    void createOrderMenus_Fail_ByDuplicatedOrderMenu() {
        // given
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu(Menu.TAPAS, 4),
                new OrderMenu(Menu.TAPAS, 6),
                new OrderMenu(Menu.T_BONE_STEAK, 10)
        );

        // when, then
        Assertions.assertThatThrownBy(() -> new OrderMenus(orderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("음료수만 주문한 경우 예외가 발생한다.")
    @Test
    void createOrderMenus_Fail_ByOnlyBeverage() {
        // given
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu(Menu.CHAMPAGNE, 8),
                new OrderMenu(Menu.COKE_ZERO, 2),
                new OrderMenu(Menu.RED_WINE, 10)
        );

        // when, then
        Assertions.assertThatThrownBy(() -> new OrderMenus(orderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }
}
