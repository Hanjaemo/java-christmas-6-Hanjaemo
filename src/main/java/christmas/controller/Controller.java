package christmas.controller;

import christmas.BenefitDetailsDto;
import christmas.GiveawayMenusDto;
import christmas.OrderMenusDto;
import christmas.domain.BenefitDetails;
import christmas.domain.event.ChristmasDiscountEventManager;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventBadgeAssigner;
import christmas.domain.ExpectedPaymentAmountCalculator;
import christmas.domain.event.GiveawayEventManager;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.domain.event.SpecialDayDiscountEventManager;
import christmas.domain.SpecialDays;
import christmas.domain.VisitDay;
import christmas.domain.event.WeekDiscountEventManager;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Controller {

    public void run() {
        OutputView.printGreetingMessage();
        VisitDay visitDay = repeatReadForInvalid(this::createVisitDay);
        Order order = repeatReadForInvalid(this::createOrder);

        OutputView.printPreviewOfEventBenefitsMessage(visitDay.getVisitDay());

        OutputView.printOrderMenus(new OrderMenusDto(order.getOrderMenus()));
        OutputView.printTotalOrderAmountBeforeDiscount(order.calculateTotalOrderAmount());

        BenefitDetails benefitDetails = new BenefitDetails();
        if (order.calculateTotalOrderAmount() >= 10000) {
            ChristmasDiscountEventManager christmasDiscountEventManager =
                    new ChristmasDiscountEventManager(benefitDetails);
            christmasDiscountEventManager.applyEvent(visitDay);

            WeekDiscountEventManager weekDiscountEventManager =
                    new WeekDiscountEventManager(benefitDetails, order);
            weekDiscountEventManager.applyEvent(visitDay);

            SpecialDayDiscountEventManager specialDayDiscountEventManager =
                    new SpecialDayDiscountEventManager(benefitDetails, new SpecialDays());
            specialDayDiscountEventManager.applyEvent(visitDay);

            GiveawayEventManager giveawayEventManager =
                    new GiveawayEventManager(benefitDetails, order);
            giveawayEventManager.applyEvent(visitDay);
        }

        OutputView.printGiveawayMenu(new GiveawayMenusDto(benefitDetails.getGiveawayMenus()));
        OutputView.printBenefitDetails(new BenefitDetailsDto(benefitDetails.getAppliedEvents()));
        OutputView.printTotalBenefitAmount(benefitDetails.calculateTotalBenefitAmount());

        ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator = new ExpectedPaymentAmountCalculator();
        OutputView.printExpectedPaymentAmountAfterDiscount(
                expectedPaymentAmountCalculator.calculateExpectedPaymentAmount(
                        order.calculateTotalOrderAmount(),
                        benefitDetails.calculateTotalBenefitAmount()
                )
        );

        EventBadgeAssigner eventBadgeAssigner = new EventBadgeAssigner();
        EventBadge eventBadge = eventBadgeAssigner.assignEventBadge(benefitDetails.calculateTotalBenefitAmount());
        OutputView.printDecemberEventBadge(eventBadge.toString());
    }

    private Order createOrder() {
        String[] split = InputView.readOrderMenus().split(",");
        List<OrderMenu> orderMenus = Arrays.stream(split)
                .map(orderMenu -> orderMenu.split("-"))
                .map(orderMenu -> new OrderMenu(Menu.from(orderMenu[0]), Integer.parseInt(orderMenu[1])))
                .collect(Collectors.toList());
        return new Order(orderMenus);
    }

    private VisitDay createVisitDay() {
        return new VisitDay(InputView.readVisitDay());
    }

    private <T> T repeatReadForInvalid(Supplier<T> reader) {
        try {
            return reader.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return repeatReadForInvalid(reader);
        }
    }
}
