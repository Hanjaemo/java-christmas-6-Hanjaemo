package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDayDiscountEventManagerTest {

    private static final int DISCOUNT_AMOUNT = 1_000;

    @DisplayName("방문 날짜가 특별한 날이면 특별 할인 이벤트를 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void applyEvent_Success_ByVisitDayIsSpecialDay(int visitDay) {
        // given
        BenefitDetails benefitDetails = new BenefitDetails();
        EventManager eventManager = new SpecialDayDiscountEventManager(benefitDetails, new SpecialDays());

        // when
        int discountAmount = eventManager.applyEvent(createVisitDay(visitDay));

        // then
        Assertions.assertThat(discountAmount).isEqualTo(DISCOUNT_AMOUNT);
    }

    @DisplayName("방문 날짜가 특별한 날이 아니면 특별 할인 이벤트를 적용하지 않으며, 할인 금액은 0이다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 12, 18, 22, 26, 30})
    void applyEvent_ReturnZero_ByVisitDayIsNotSpecialDay(int visitDay) {
        // given
        BenefitDetails benefitDetails = new BenefitDetails();
        EventManager eventManager = new SpecialDayDiscountEventManager(benefitDetails, new SpecialDays());

        // when
        int discountAmount = eventManager.applyEvent(createVisitDay(visitDay));

        // then
        Assertions.assertThat(discountAmount).isEqualTo(0);
    }

    VisitDay createVisitDay(int visitDay) {
        return new VisitDay(visitDay);
    }
}
