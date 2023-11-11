package christmas.domain;

import java.util.EnumMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveawayEventManagerTest {

    @DisplayName("할인 전 총주문 금액이 12만원 이상이면 할인 이벤트를 적용한다.")
    @Test
    void applyEvent_Success_ByTotalOrderAmountIsMoreThan120_000() {
        // given
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(Menu.MUSHROOM_SOUP, 1);
        orderMenus.put(Menu.T_BONE_STEAK, 1);
        orderMenus.put(Menu.RED_WINE, 1);
        EventManager eventManager = new GiveawayEventManager(new BenefitDetails(), new Order(orderMenus));

        // when
        int discount = eventManager.applyEvent(4);

        // then
        Assertions.assertThat(discount).isEqualTo(25_000);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 이하면 할인 이벤트를 적용한다.")
    @Test
    void applyEvent_ReturnZero_ByTotalOrderAmountIsLessThan120_000() {
        // given
        Map<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(Menu.MUSHROOM_SOUP, 1);
        orderMenus.put(Menu.RED_WINE, 1);
        EventManager eventManager = new GiveawayEventManager(new BenefitDetails(), new Order(orderMenus));

        // when
        int discount = eventManager.applyEvent(4);

        // then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}