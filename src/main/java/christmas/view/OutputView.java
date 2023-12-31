package christmas.view;

import christmas.dto.BenefitDetailsDto;
import christmas.dto.GiveawayMenusDto;
import christmas.dto.OrderMenusDto;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String NOT_EXIST_MESSAGE = "없음";

    private OutputView() {
    }

    public static void printGreetingMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printPreviewOfEventBenefitsMessage(int visitDay) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%s", visitDay, NEW_LINE);
        printNewLine();
    }

    public static void printOrderMenus(OrderMenusDto orderMenusDto) {
        System.out.println("<주문 메뉴>");
        repeatPrintMenus(orderMenusDto.getMenus());
        printNewLine();
    }

    private static void repeatPrintMenus(Map<String, Integer> menus) {
        for (String menuName : menus.keySet()) {
            System.out.printf("%s %d개%s", menuName, menus.get(menuName), NEW_LINE);
        }
    }

    public static void printTotalOrderAmountBeforeDiscount(int totalOrderAmountBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(convertNumberToKoreanWonFormat(totalOrderAmountBeforeDiscount));
        printNewLine();
    }

    private static String convertNumberToKoreanWonFormat(int originalNumber) {
        return new DecimalFormat("#,###원").format(originalNumber);
    }

    public static void printGiveawayMenu(GiveawayMenusDto giveawayMenusDto) {
        System.out.println("<증정 메뉴>");
        Map<String, Integer> menus = giveawayMenusDto.getMenus();
        if (isNotExist(menus)) {
            System.out.println(NOT_EXIST_MESSAGE);
            printNewLine();
            return;
        }
        repeatPrintMenus(giveawayMenusDto.getMenus());
        printNewLine();
    }

    private static boolean isNotExist(Map<String, Integer> map) {
        return map.size() == 0;
    }

    public static void printBenefitDetails(BenefitDetailsDto benefitDetailsDto) {
        System.out.println("<혜택 내역>");
        Map<String, Integer> benefitDetails = benefitDetailsDto.getBenefitDetails();
        if (isNotExist(benefitDetails)) {
            System.out.println(NOT_EXIST_MESSAGE);
            printNewLine();
            return;
        }
        repeatPrintEventAndDiscountAmount(benefitDetails);
        printNewLine();
    }

    private static void repeatPrintEventAndDiscountAmount(Map<String, Integer> benefitDetails) {
        for (String benefitName : benefitDetails.keySet()) {
            System.out.printf("%s: %s%s",
                    benefitName, convertNumberToKoreanWonFormat(-benefitDetails.get(benefitName)), NEW_LINE);
        }
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println("<총혜택 금액>");
        System.out.println(convertNumberToKoreanWonFormat(-totalBenefitAmount));
        printNewLine();
    }

    public static void printExpectedPaymentAmountAfterDiscount(int expectedPaymentAmountAfterDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(convertNumberToKoreanWonFormat(expectedPaymentAmountAfterDiscount));
        printNewLine();
    }

    public static void printDecemberEventBadge(String badgeName) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeName);
    }

    private static void printNewLine() {
        System.out.print(NEW_LINE);
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
