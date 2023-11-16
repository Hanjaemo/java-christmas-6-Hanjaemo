package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExpectedPaymentAmountCalculatorTest {

    @DisplayName("할인 후 예상 결제 금액은 총주문 금액에서 총혜택 금액을 뺀 값과 같다.")
    @Test
    void calculateExpectedPaymentAmount_Success() {
        // given
        int totalOrderAmount = 167_000;
        int totalBenefitAmount = 31_246;

        ExpectedPaymentAmountCalculator calculator = new ExpectedPaymentAmountCalculator();

        // when
        int expectedPaymentAmount = calculator.calculateExpectedPaymentAmount(totalOrderAmount, totalBenefitAmount);

        // then
        Assertions.assertThat(expectedPaymentAmount).isEqualTo(totalOrderAmount - totalBenefitAmount);
    }
}
