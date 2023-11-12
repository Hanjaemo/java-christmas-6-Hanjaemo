package christmas.domain;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    Map<Menu, Integer> orderMenus;
    Order order;

    @BeforeEach
    void setUp() {
        orderMenus = Map.of(
                Menu.T_BONE_STEAK, 1,
                Menu.CHOCOLATE_CAKE, 2,
                Menu.COKE_ZERO, 2
        );
        order = new Order(orderMenus);
    }

    @DisplayName("주문한 메뉴 중에서 카테고리가 메인인 메뉴들의 개수를 센다.")
    @Test
    void countMainMenus_Success() {
        // when
        int mainMenusCount = order.countMainMenus();

        // then
        Assertions.assertThat(mainMenusCount).isEqualTo(1);
    }

    @DisplayName("주문한 메뉴 중에서 카테고리가 디저트인 메뉴들의 개수를 센다.")
    @Test
    void countDessertMenus_Success() {
        // when
        int mainMenusCount = order.countDessertMenus();

        // then
        Assertions.assertThat(mainMenusCount).isEqualTo(2);
    }

    @DisplayName("할인 전 총주문 금액을 계산한다.")
    @Test
    void calculateTotalOrderAmount_Success() {
        // when
        int totalOrderAmount = order.calculateTotalOrderAmount();

        // then
        Assertions.assertThat(totalOrderAmount).isEqualTo(91_000);
    }
}