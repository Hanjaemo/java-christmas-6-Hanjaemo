package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderMenusDto;
import christmas.error.ErrorMessage;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {

    private static final String ORDER_MENUS_DELIMITER = ",";
    private static final String MENU_AND_QUANTITY_DELIMITER = "-";
    private static final Pattern ORDER_MENUS_REGEX =
            Pattern.compile("^([가-힣a-zA-Z0-9]+%s\\d+)(%s[가-힣a-zA-Z0-9]+%s\\d+)*$"
                    .formatted(MENU_AND_QUANTITY_DELIMITER, ORDER_MENUS_DELIMITER, MENU_AND_QUANTITY_DELIMITER));

    private InputView() {
    }

    public static int readVisitDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        validateEmpty(input);
        return toInt(input);
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 값을 입력하지 않았습니다. 다시 입력해 주세요.");
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VISIT_DAY.getMessage());
        }
    }

    public static OrderMenusDto readOrderMenus() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        validateEmpty(input);
        validateOrderMenusRegex(input);
        Map<String, Integer> orderMenus = Arrays.stream(input.split(ORDER_MENUS_DELIMITER))
                .map(orderMenu -> orderMenu.split(MENU_AND_QUANTITY_DELIMITER))
                .collect(Collectors.toMap(
                        orderMenu -> orderMenu[0],
                        orderMenu -> Integer.parseInt(orderMenu[1]))
                );
        return OrderMenusDto.from(orderMenus);
    }

    private static void validateOrderMenusRegex(String input) {
        if (invalidOrderRegex(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private static boolean invalidOrderRegex(String input) {
        return !ORDER_MENUS_REGEX.matcher(input).matches();
    }
}
