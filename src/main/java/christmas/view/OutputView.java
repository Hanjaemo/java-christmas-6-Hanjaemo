package christmas.view;

import java.util.Map;

import christmas.OrderMenusDto;

public class OutputView {

    private OutputView() {
    }

    public static void printGreetingMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printPreviewOfEventBenefitsMessage(int visitDay) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", visitDay);
    }

    public static void printOrderMenus(OrderMenusDto orderMenusDto) {
        System.out.println("<주문 메뉴>");
        Map<String, Integer> menus = orderMenusDto.getMenus();
        for (String menuName : menus.keySet()) {
            System.out.printf("%s %d개%n", menuName, menus.get(menuName));
        }
    }
}
