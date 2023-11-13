package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.VisitDay;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekDiscountEventManagerTest {

    OrderMenus orderMenus;
    EventManager eventManager;

    @BeforeEach
    void init() {
        orderMenus = new OrderMenus(
                List.of(new OrderMenu(Menu.T_BONE_STEAK, 1), new OrderMenu(Menu.CHOCOLATE_CAKE, 2))
        );
        eventManager = new WeekDiscountEventManager();
    }

    @DisplayName("방문 날짜가 평일이면 평일 할인 이벤트를 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 12, 25, 31})
    void applyEvent_Success_ByVisitDayIsWeekday(int visitDay) {
        // given
        EventContext eventContext = new EventContext(createVisitDay(visitDay), orderMenus);

        // when
        int discountAmount = eventManager.applyEvent(eventContext, new BenefitDetails());

        // then
        Assertions.assertThat(discountAmount).isEqualTo(2023 * 2);
    }

    @DisplayName("방문 날짜가 주말이면 주말 할인 이벤트를 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 9, 22, 30})
    void applyEvent_Success_ByVisitDayIsWeekend(int visitDay) {
        // given
        EventContext eventContext = new EventContext(createVisitDay(visitDay), orderMenus);

        // when
        int discountAmount = eventManager.applyEvent(eventContext, new BenefitDetails());

        // then
        Assertions.assertThat(discountAmount).isEqualTo(2023);
    }

    VisitDay createVisitDay(int visitDay) {
        return new VisitDay(visitDay);
    }
}
