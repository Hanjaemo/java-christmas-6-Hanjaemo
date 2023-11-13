package christmas.domain.menu;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class MenuTest {

    @DisplayName("메뉴 이름에 해당하는 메뉴를 반환한다.")
    @ParameterizedTest
    @MethodSource("parametersForMenuFromMenuNameTest")
    void menuFromMenuName_ReturnMenu(String menuName, Menu menu) {
        // when, then
        Assertions.assertThat(Menu.from(menuName)).isEqualTo(menu);
    }

    static Stream<Arguments> parametersForMenuFromMenuNameTest() {
        return Stream.of(
                Arguments.of("타파스", Menu.TAPAS),
                Arguments.of("티본스테이크", Menu.T_BONE_STEAK),
                Arguments.of("초코케이크", Menu.CHOCOLATE_CAKE),
                Arguments.of("샴페인", Menu.CHAMPAGNE),
                Arguments.of("제로콜라", Menu.COKE_ZERO)
        );
    }

    @DisplayName("주문한 메뉴 이름이 존재하지 않는 메뉴인 경우 예외가 발생한다.")
    @Test
    void notFoundMenu_ByOrderedMenuName() {
        // when, then
        Assertions.assertThatThrownBy(() -> Menu.from("브로콜리"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴의 카테고리가 메인이면 true를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"T_BONE_STEAK", "BARBECUE_RIBS", "SEAFOOD_PASTA", "CHRISTMAS_PASTA"})
    void isMain_True(Menu menu) {
        // when, then
        Assertions.assertThat(menu.isMain()).isTrue();
    }

    @DisplayName("메뉴의 카테고리가 메인이 아니면 false를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"TAPAS", "ICE_CREAM", "RED_WINE"})
    void isMain_False(Menu menu) {
        // when, then
        Assertions.assertThat(menu.isMain()).isFalse();
    }

    @DisplayName("메뉴의 카테고리가 디저트면 true를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"CHOCOLATE_CAKE", "ICE_CREAM"})
    void isDessert_True(Menu menu) {
        // when, then
        Assertions.assertThat(menu.isDessert()).isTrue();
    }

    @DisplayName("메뉴의 카테고리가 디저트가 아니면 false를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"TAPAS", "SEAFOOD_PASTA", "COKE_ZERO"})
    void isDessert_False(Menu menu) {
        // when, then
        Assertions.assertThat(menu.isDessert()).isFalse();
    }
}