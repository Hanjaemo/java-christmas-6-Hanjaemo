package christmas.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.domain.event.ChristmasDiscountEventManager;
import christmas.domain.event.EventContext;
import christmas.domain.event.EventManager;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;

class ChristmasDiscountEventManagerTest {

    @DisplayName("방문 날짜가 1 이상 25 이하인 경우 크리스마스 디데이 할인 이벤트를 적용한다.")
    @ParameterizedTest
    @CsvSource(value = {"3,1200", "5,1400", "10,1900", "24,3300", "25,3400"})
    void applyEvent_Success_ByVisitDayIsWithinChristmasPeriod(int visitDay, int expectedDiscountAmount) {
        // given
        OrderMenus orderMenus = new OrderMenus(List.of(new OrderMenu(Menu.CHAMPAGNE, 1)));
        EventContext eventContext = new EventContext(createVisitDay(visitDay), orderMenus);
        EventManager christmasDiscountEventManager = new ChristmasDiscountEventManager();

        // when
        int discountAmount = christmasDiscountEventManager.applyEvent(eventContext, new BenefitDetails());

        // then
        Assertions.assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
    }

    @DisplayName("방문 날짜가 1 이상 25 이하가 아닌 경우 크리스마스 디데이 할인 이벤트를 적용하지 않으며, 할인 금액은 0이 된다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 29, 30, 31})
    void applyEvent_ReturnZero_ByVisitDayIsNotWithinChristmasPeriod(int visitDay) {
        // given
        OrderMenus orderMenus = new OrderMenus(List.of(new OrderMenu(Menu.CHAMPAGNE, 1)));
        EventContext eventContext = new EventContext(createVisitDay(visitDay), orderMenus);
        EventManager christmasDiscountEventManager = new ChristmasDiscountEventManager();

        // when
        int discountAmount = christmasDiscountEventManager.applyEvent(eventContext, new BenefitDetails());

        // then
        Assertions.assertThat(discountAmount).isEqualTo(0);
    }

    VisitDay createVisitDay(int visitDay) {
        return new VisitDay(visitDay);
    }
}
