package christmas.domain;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주문한 메뉴 중에서 카테고리가 메인인 메뉴들의 개수를 센다.")
    @Test
    void countMainMenus_Success() {
        // given
        Map<Menu, Integer> orderMenus = Map.of(
                Menu.SEAFOOD_PASTA, 1,
                Menu.CHAMPAGNE, 2
        );
        Order order = new Order(orderMenus);

        // when
        int mainMenusCount = order.countMainMenus();

        // then
        Assertions.assertThat(mainMenusCount).isEqualTo(1);
    }

    @DisplayName("주문한 메뉴 중에서 카테고리가 디저트인 메뉴들의 개수를 센다.")
    @Test
    void countDessertMenus_Success() {
        // given
        Map<Menu, Integer> orderMenus = Map.of(
                Menu.TAPAS, 1,
                Menu.CHOCOLATE_CAKE, 2,
                Menu.COKE_ZERO, 2
        );
        Order order = new Order(orderMenus);

        // when
        int mainMenusCount = order.countDessertMenus();

        // then
        Assertions.assertThat(mainMenusCount).isEqualTo(2);
    }

}