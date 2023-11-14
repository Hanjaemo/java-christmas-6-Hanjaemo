package christmas.service;

import christmas.domain.BenefitDetails;
import christmas.domain.ExpectedPaymentAmountCalculator;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventBadgeAssigner;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private static final String MENU_AND_QUANTITY_DELIMITER = "-";

    private final ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator;
    private final EventBadgeAssigner eventBadgeAssigner;

    public Service(
            ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator,
            EventBadgeAssigner eventBadgeAssigner) {
        this.expectedPaymentAmountCalculator = expectedPaymentAmountCalculator;
        this.eventBadgeAssigner = eventBadgeAssigner;
    }

    public OrderMenus order(List<String> orderMenus) {
        return new OrderMenus(convertToOrderMenuList(orderMenus));
    }

    private List<OrderMenu> convertToOrderMenuList(List<String> orderMenus) {
        return orderMenus.stream()
                .map(orderMenu -> orderMenu.split(MENU_AND_QUANTITY_DELIMITER))
                .map(orderMenu -> new OrderMenu(Menu.from(orderMenu[0]), Integer.parseInt(orderMenu[1])))
                .collect(Collectors.toList());
    }

    public int calculateTotalOrderAmount(OrderMenus orderMenus) {
        return orderMenus.calculateTotalOrderAmount();
    }

    public int calculateExpectedPaymentAmount(OrderMenus orderMenus, BenefitDetails benefitDetails) {
        return expectedPaymentAmountCalculator.calculateExpectedPaymentAmount(
                orderMenus.calculateTotalOrderAmount(),
                benefitDetails.calculateTotalBenefitAmount()
        );
    }

    public EventBadge assignEventBadge(BenefitDetails benefitDetails) {
        return eventBadgeAssigner.assignEventBadge(benefitDetails.calculateTotalBenefitAmount());
    }
}
