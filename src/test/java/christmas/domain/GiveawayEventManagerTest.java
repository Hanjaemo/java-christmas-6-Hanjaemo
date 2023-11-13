package christmas.domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.event.EventManager;
import christmas.domain.event.GiveawayEventManager;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;

class GiveawayEventManagerTest {

    @DisplayName("할인 전 총주문 금액이 12만원 이상이면 할인 이벤트를 적용한다.")
    @Test
    void applyEvent_Success_ByTotalOrderAmountIsMoreThan120_000() {
        // given
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(new OrderMenu(Menu.MUSHROOM_SOUP, 1));
        orderMenus.add(new OrderMenu(Menu.T_BONE_STEAK, 1));
        orderMenus.add(new OrderMenu(Menu.RED_WINE, 1));
        EventManager eventManager = new GiveawayEventManager(new BenefitDetails(), new Order(orderMenus));

        // when
        int discount = eventManager.applyEvent(createVisitDay(4));

        // then
        Assertions.assertThat(discount).isEqualTo(25_000);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 이하면 할인 이벤트를 적용한다.")
    @Test
    void applyEvent_ReturnZero_ByTotalOrderAmountIsLessThan120_000() {
        // given
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu(Menu.MUSHROOM_SOUP, 1),
                new OrderMenu(Menu.RED_WINE, 1)
        );
        EventManager eventManager = new GiveawayEventManager(new BenefitDetails(), new Order(orderMenus));

        // when
        int discount = eventManager.applyEvent(createVisitDay(5));

        // then
        Assertions.assertThat(discount).isEqualTo(0);
    }

    VisitDay createVisitDay(int visitDay) {
        return new VisitDay(visitDay);
    }
}
