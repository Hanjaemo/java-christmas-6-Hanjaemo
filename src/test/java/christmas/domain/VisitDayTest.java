package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDayTest {

    @DisplayName("식당 예상 방문 날짜가 1보다 작으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void createVisitDay_Fail_ByLessThan1(int visitDay) {
        // when, then
        Assertions.assertThatThrownBy(() -> new VisitDay(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("식당 예상 방문 날짜가 31보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {32, 45, 50})
    void createVisitDay_Fail_ByMoreThan31(int visitDay) {
        // when, then
        Assertions.assertThatThrownBy(() -> new VisitDay(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}