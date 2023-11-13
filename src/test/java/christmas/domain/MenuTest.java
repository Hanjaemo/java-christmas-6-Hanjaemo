package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import christmas.domain.menu.Menu;

class MenuTest {

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