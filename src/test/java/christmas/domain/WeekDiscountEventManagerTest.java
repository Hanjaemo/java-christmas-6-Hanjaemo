package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekDiscountEventManagerTest {

    Map<Menu, Integer> orderMenus;
    EventManager eventManager;

    @BeforeEach
    void init() {
        orderMenus = Map.of(
                Menu.T_BONE_STEAK, 1,
                Menu.CHOCOLATE_CAKE, 2
        );
        eventManager = new WeekDiscountEventManager(new BenefitDetails(), new Order(orderMenus));
    }

    @DisplayName("방문 날짜가 평일이면 평일 할인 이벤트를 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 12, 25, 31})
    void applyEvent_Success_ByVisitDayIsWeekday(int visitDay) {
        // when
        int discountAmount = eventManager.applyEvent(visitDay);

        // then
        Assertions.assertThat(discountAmount).isEqualTo(2023 * 2);
    }

    @DisplayName("방문 날짜가 주말이면 주말 할인 이벤트를 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 9, 22, 30})
    void applyEvent_Success_ByVisitDayIsWeekend(int visitDay) {
        // when
        int discountAmount = eventManager.applyEvent(visitDay);

        // then
        Assertions.assertThat(discountAmount).isEqualTo(2023);
    }

}