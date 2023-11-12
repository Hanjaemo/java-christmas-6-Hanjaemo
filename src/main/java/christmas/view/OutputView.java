package christmas.view;

import christmas.BenefitDetailsDto;
import christmas.GiveawayMenusDto;
import christmas.OrderMenusDto;
import christmas.domain.BenefitDetails;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String NOT_EXIST_MESSAGE = "없음";

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
        repeatPrintMenus(orderMenusDto.getMenus());
    }

    public static void printTotalOrderAmountBeforeDiscount(int totalOrderAmountBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(convertNumberToKoreanWonFormat(totalOrderAmountBeforeDiscount));
    }

    private static String convertNumberToKoreanWonFormat(int originalNumber) {
        return new DecimalFormat("#,###원").format(originalNumber);
    }

    public static void printGiveawayMenu(GiveawayMenusDto giveawayMenusDto) {
        System.out.println("<증정 메뉴>");
        Map<String, Integer> menus = giveawayMenusDto.getMenus();
        if (isNotExist(menus)) {
            System.out.println(NOT_EXIST_MESSAGE);
            return;
        }
        repeatPrintMenus(giveawayMenusDto.getMenus());
    }

    private static boolean isNotExist(Map<String, Integer> map) {
        return map.size() == 0;
    }

    private static void repeatPrintMenus(Map<String, Integer> menus) {
        for (String menuName : menus.keySet()) {
            System.out.printf("%s %d개%n", menuName, menus.get(menuName));
        }
    }

    public static void printBenefitDetails(BenefitDetailsDto benefitDetailsDto) {
        System.out.println("<혜택 내역>");
        Map<String, Integer> benefitDetails = benefitDetailsDto.getBenefitDetails();
        if (isNotExist(benefitDetails)) {
            System.out.println(NOT_EXIST_MESSAGE);
            return;
        }
        for (String benefitName : benefitDetails.keySet()) {
            System.out.printf("%s: %s%n",
                    benefitName, convertNumberToKoreanWonFormat(-benefitDetails.get(benefitName)));
        }
    }
}
