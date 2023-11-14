package christmas.service;

import christmas.domain.BenefitDetails;
import christmas.domain.ExpectedPaymentAmountCalculator;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventBadgeAssigner;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import christmas.dto.OrderMenusDto;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private final ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator;
    private final EventBadgeAssigner eventBadgeAssigner;

    public Service(
            ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator,
            EventBadgeAssigner eventBadgeAssigner) {
        this.expectedPaymentAmountCalculator = expectedPaymentAmountCalculator;
        this.eventBadgeAssigner = eventBadgeAssigner;
    }

    public OrderMenus order(OrderMenusDto orderMenusDto) {
        return new OrderMenus(convertToOrderMenuList(orderMenusDto));
    }

    private List<OrderMenu> convertToOrderMenuList(OrderMenusDto orderMenusDto) {
        return orderMenusDto.getMenus().entrySet()
                .stream()
                .map(orderMenus -> new OrderMenu(Menu.from(orderMenus.getKey()), orderMenus.getValue()))
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
