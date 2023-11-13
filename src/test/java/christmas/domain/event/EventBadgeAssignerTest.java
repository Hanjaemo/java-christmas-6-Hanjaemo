package christmas.domain.event;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventBadgeAssignerTest {

    @DisplayName("총혜택 금액에 따라 12월 이벤트 배지를 부여한다.")
    @ParameterizedTest
    @MethodSource("parametersForAssignEventBadgeTest")
    void assignEventBadge_Success(int totalBenefitAmount, EventBadge expectedEventBadge) {
        // given
        EventBadgeAssigner eventBadgeAssigner = new EventBadgeAssigner();

        // when
        EventBadge eventBadge = eventBadgeAssigner.assignEventBadge(totalBenefitAmount);

        // then
        Assertions.assertThat(eventBadge).isEqualTo(expectedEventBadge);
    }

    static Stream<Arguments> parametersForAssignEventBadgeTest() {
        return Stream.of(
                Arguments.of(25_000, EventBadge.SANTA),
                Arguments.of(11_000, EventBadge.TREE),
                Arguments.of(6_500, EventBadge.STAR),
                Arguments.of(3_000, EventBadge.MISS)
        );
    }
}