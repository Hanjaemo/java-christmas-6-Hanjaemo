package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.error.ErrorMessage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class OrderMenuTest {

    @DisplayName("메뉴 수량이 최솟값보다 작은 경우 예외가 발생한다.")
    @Test
    void createOrderMenu_Fail_ByQuantityIsLessThanMinimum() {
        // when, then
        Assertions.assertThatThrownBy(() -> new OrderMenu(Menu.BARBECUE_RIBS, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("메뉴 수량이 최댓값보다 큰 경우 예외가 발생한다.")
    @Test
    void createOrderMenu_Fail_ByQuantityIsMoreThanMaximum() {
        // when, then
        Assertions.assertThatThrownBy(() -> new OrderMenu(Menu.BARBECUE_RIBS, 21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문한 메뉴의 카테고리가 메인이면 true를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"T_BONE_STEAK", "BARBECUE_RIBS", "SEAFOOD_PASTA", "CHRISTMAS_PASTA"})
    void isMain_True(Menu menu) {
        // given
        OrderMenu orderMenu = new OrderMenu(menu, 4);

        // when, then
        Assertions.assertThat(orderMenu.isMenuMain()).isTrue();
    }

    @DisplayName("주문한 메뉴의 카테고리가 메인이 아니면 false를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"TAPAS", "ICE_CREAM", "RED_WINE"})
    void isMain_False(Menu menu) {
        // given
        OrderMenu orderMenu = new OrderMenu(menu, 4);

        // when, then
        Assertions.assertThat(orderMenu.isMenuMain()).isFalse();
    }

    @DisplayName("주문한 메뉴의 카테고리가 디저트면 true를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"CHOCOLATE_CAKE", "ICE_CREAM"})
    void isDessert_True(Menu menu) {
        // given
        OrderMenu orderMenu = new OrderMenu(menu, 4);

        // when, then
        Assertions.assertThat(orderMenu.isMenuDessert()).isTrue();
    }

    @DisplayName("주문한 메뉴의 카테고리가 디저트가 아니면 false를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"TAPAS", "SEAFOOD_PASTA", "COKE_ZERO"})
    void isDessert_False(Menu menu) {
        // given
        OrderMenu orderMenu = new OrderMenu(menu, 4);

        // when, then
        Assertions.assertThat(orderMenu.isMenuDessert()).isFalse();
    }

    @DisplayName("주문한 메뉴의 수량에 따른 가격을 계산한다.")
    @Test
    void calculatePrice_Success() {
        // given
        OrderMenu orderMenu = new OrderMenu(Menu.COKE_ZERO, 3);

        // when, then
        Assertions.assertThat(orderMenu.calculatePrice()).isEqualTo(9_000);
    }
}