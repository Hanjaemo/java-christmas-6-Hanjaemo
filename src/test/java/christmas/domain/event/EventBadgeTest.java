package christmas.domain.event;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventBadgeTest {

    @DisplayName("총혜택 금액이 이벤트 배지 부여 조건과 일치하면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("parametersForMatchesTotalBenefitAmountTest")
    void matchesTotalBenefitAmount_True(EventBadge eventBadge, int totalBenefitAmount) {
        // when, then
        Assertions.assertThat(eventBadge.matchesTotalBenefitAmount(totalBenefitAmount)).isTrue();
    }

    static Stream<Arguments> parametersForMatchesTotalBenefitAmountTest() {
        return Stream.of(
                Arguments.of(EventBadge.SANTA, 25_000),
                Arguments.of(EventBadge.TREE, 11_000),
                Arguments.of(EventBadge.STAR, 6_500),
                Arguments.of(EventBadge.MISS, 3_000)
        );
    }

    @DisplayName("총혜택 금액이 이벤트 배지 부여 조건과 일치하지 않으면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("parametersForNotMatchesTotalBenefitAmountTest")
    void matchesTotalBenefitAmount_False(EventBadge eventBadge, int totalBenefitAmount) {
        // when, then
        Assertions.assertThat(eventBadge.matchesTotalBenefitAmount(totalBenefitAmount)).isFalse();
    }

    static Stream<Arguments> parametersForNotMatchesTotalBenefitAmountTest() {
        return Stream.of(
                Arguments.of(EventBadge.SANTA, 3_000),
                Arguments.of(EventBadge.TREE, 6_500),
                Arguments.of(EventBadge.STAR, 33_000),
                Arguments.of(EventBadge.MISS, 8_000)
        );
    }
}
