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
        // when, then
        Assertions.assertThatThrownBy(() -> new OrderMenus(List.of(
                        new OrderMenu(Menu.CHAMPAGNE, 8),
                        new OrderMenu(Menu.TAPAS, 6),
                        new OrderMenu(Menu.T_BONE_STEAK, 10)
                        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }
}
