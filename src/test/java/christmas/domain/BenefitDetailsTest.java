package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitDetailsTest {

    @DisplayName("총혜택 금액은 적용된 이벤트에 따른 할인 금액을 모두 더한 값과 같다.")
    @Test
    void calculateTotalBenefitAmount_Success() {
        // given
        BenefitDetails benefitDetails = new BenefitDetails();
        benefitDetails.addEvent(Event.GIVEAWAY, 25_000);
        benefitDetails.addEvent(Event.CHRISTMAS_DISCOUNT, 2_000);

        // when
        int totalBenefitAmount = benefitDetails.calculateTotalBenefitAmount();

        // then
        Assertions.assertThat(totalBenefitAmount).isEqualTo(27_000);
    }

}