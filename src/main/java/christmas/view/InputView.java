package christmas.view;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final Pattern ORDER_MENUS_REGEX =
            Pattern.compile("^([가-힣a-zA-Z0-9]+-\\d+)(,[가-힣a-zA-Z0-9]+-\\d+)*$");

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
            throw new IllegalArgumentException("[ERROR] 값을 입력하지 않았습니다.");
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
    }

    public static Map<String, Integer> readOrderMenus() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        validateEmpty(input);
        validateOrderMenusRegex(input);
        return Arrays.stream(input.split(","))
                .map(menu -> menu.split("-"))
                .collect(Collectors.toMap(
                        menuInfo -> menuInfo[0],
                        menuInfo -> toInt(menuInfo[1])));
    }

    private static void validateOrderMenusRegex(String input) {
        if (invalidOrderRegex(input)) {
            throw new IllegalArgumentException("[ERROR] 입력 형식이 일치하지 않습니다.");
        }
    }

    private static boolean invalidOrderRegex(String input) {
        return !ORDER_MENUS_REGEX.matcher(input).matches();
    }
}
