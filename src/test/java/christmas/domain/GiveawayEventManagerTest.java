package christmas.domain;

import christmas.domain.event.EventContext;
import christmas.domain.event.EventManager;
import christmas.domain.event.GiveawayEventManager;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveawayEventManagerTest {

    @DisplayName("할인 전 총주문 금액이 12만원 이상이면 할인 이벤트를 적용한다.")
    @Test
    void applyEvent_Success_ByTotalOrderAmountIsMoreThan120_000() {
        // given
        List<OrderMenu> orderMenuList = new ArrayList<>();
        orderMenuList.add(new OrderMenu(Menu.MUSHROOM_SOUP, 1));
        orderMenuList.add(new OrderMenu(Menu.T_BONE_STEAK, 1));
        orderMenuList.add(new OrderMenu(Menu.RED_WINE, 1));
        OrderMenus orderMenus = new OrderMenus(orderMenuList);
        EventContext eventContext = new EventContext(new VisitDay(5), orderMenus);
        EventManager eventManager = new GiveawayEventManager();

        // when
        int discount = eventManager.applyEvent(eventContext, new BenefitDetails());

        // then
        Assertions.assertThat(discount).isEqualTo(25_000);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 이하면 할인 이벤트를 적용한다.")
    @Test
    void applyEvent_ReturnZero_ByTotalOrderAmountIsLessThan120_000() {
        // given
        List<OrderMenu> orderMenuList = new ArrayList<>();
        orderMenuList.add(new OrderMenu(Menu.MUSHROOM_SOUP, 1));
        orderMenuList.add(new OrderMenu(Menu.RED_WINE, 1));
        OrderMenus orderMenus = new OrderMenus(orderMenuList);
        EventContext eventContext = new EventContext(new VisitDay(5), orderMenus);
        EventManager eventManager = new GiveawayEventManager();

        // when
        int discount = eventManager.applyEvent(eventContext, new BenefitDetails());

        // then
        Assertions.assertThat(discount).isEqualTo(0);
    }

    VisitDay createVisitDay(int visitDay) {
        return new VisitDay(visitDay);
    }
}
