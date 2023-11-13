package christmas.domain;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekDiscountEventManagerTest {

    List<OrderMenu> orderMenus;
    EventManager eventManager;

    @BeforeEach
    void init() {
        orderMenus = List.of(
                new OrderMenu(Menu.T_BONE_STEAK, 1),
                new OrderMenu(Menu.CHOCOLATE_CAKE, 2)
        );
        eventManager = new WeekDiscountEventManager(new BenefitDetails(), new Order(orderMenus));
    }

    @DisplayName("방문 날짜가 평일이면 평일 할인 이벤트를 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 12, 25, 31})
    void applyEvent_Success_ByVisitDayIsWeekday(int visitDay) {
        // when
        int discountAmount = eventManager.applyEvent(createVisitDay(visitDay));

        // then
        Assertions.assertThat(discountAmount).isEqualTo(2023 * 2);
    }

    @DisplayName("방문 날짜가 주말이면 주말 할인 이벤트를 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 9, 22, 30})
    void applyEvent_Success_ByVisitDayIsWeekend(int visitDay) {
        // when
        int discountAmount = eventManager.applyEvent(createVisitDay(visitDay));

        // then
        Assertions.assertThat(discountAmount).isEqualTo(2023);
    }

    VisitDay createVisitDay(int visitDay) {
        return new VisitDay(visitDay);
    }
}
