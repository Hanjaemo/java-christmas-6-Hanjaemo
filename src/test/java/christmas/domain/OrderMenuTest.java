package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {

    @DisplayName("")
    @Test
    void createOrderMenu_Fail_ByQuantityIsLessThanMinimum() {
        // when, then
        Assertions.assertThatThrownBy(() -> new OrderMenu(Menu.BARBECUE_RIBS, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}