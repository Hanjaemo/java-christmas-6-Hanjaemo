package christmas.domain;

import christmas.error.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDayTest {

    @DisplayName("식당 예상 방문 날짜가 1보다 작으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void createVisitDay_Fail_ByLessThan1(int visitDay) {
        // when, then
        Assertions.assertThatThrownBy(() -> createVisitDay(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_VISIT_DAY.getMessage());
    }

    @DisplayName("식당 예상 방문 날짜가 31보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {32, 45, 50})
    void createVisitDay_Fail_ByMoreThan31(int visitDay) {
        // when, then
        Assertions.assertThatThrownBy(() -> createVisitDay(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_VISIT_DAY.getMessage());
    }

    @DisplayName("식당 예상 방문 날짜가 1일부터 크리스마스(25일) 사이에 속하지 않으면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 28, 30})
    void isNotWithinChristmasPeriod_False(int visitDay) {
        // given
        VisitDay createdVisitDay = createVisitDay(visitDay);

        // when, then
        Assertions.assertThat(createdVisitDay.isNotWithinChristmasPeriod(25)).isTrue();
    }

    @DisplayName("방문 날짜를 1 감소시킨다.")
    @Test
    void decreaseVisitDay_Success() {
        // given
        VisitDay visitDay = createVisitDay(5);

        // when, then
        Assertions.assertThat(visitDay.decreaseOneDay()).isEqualTo(4);
    }

    private VisitDay createVisitDay(int visitDay) {
        return new VisitDay(visitDay);
    }
}
