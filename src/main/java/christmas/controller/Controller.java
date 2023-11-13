package christmas.controller;

import christmas.BenefitDetailsDto;
import christmas.GiveawayMenusDto;
import christmas.OrderMenusDto;
import christmas.domain.BenefitDetails;
import christmas.domain.ExpectedPaymentAmountCalculator;
import christmas.domain.SpecialDays;
import christmas.domain.VisitDay;
import christmas.domain.event.ChristmasDiscountEventManager;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventBadgeAssigner;
import christmas.domain.event.GiveawayEventManager;
import christmas.domain.event.SpecialDayDiscountEventManager;
import christmas.domain.event.WeekDiscountEventManager;
import christmas.domain.order.OrderMenus;
import christmas.service.Service;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.function.Supplier;

public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void run() {
        OutputView.printGreetingMessage();
        VisitDay visitDay = repeatReadForInvalid(this::createVisitDay);
        OrderMenus orderMenus = repeatReadForInvalid(this::order);

        OutputView.printPreviewOfEventBenefitsMessage(visitDay.getVisitDay());

        OutputView.printOrderMenus(OrderMenusDto.from(orderMenus));
        OutputView.printTotalOrderAmountBeforeDiscount(service.calculateTotalOrderAmount(orderMenus));

        BenefitDetails benefitDetails = new BenefitDetails();
        if (orderMenus.calculateTotalOrderAmount() >= 10000) {
            ChristmasDiscountEventManager christmasDiscountEventManager =
                    new ChristmasDiscountEventManager(benefitDetails);
            christmasDiscountEventManager.applyEvent(visitDay);

            WeekDiscountEventManager weekDiscountEventManager =
                    new WeekDiscountEventManager(benefitDetails, orderMenus);
            weekDiscountEventManager.applyEvent(visitDay);

            SpecialDayDiscountEventManager specialDayDiscountEventManager =
                    new SpecialDayDiscountEventManager(benefitDetails, new SpecialDays());
            specialDayDiscountEventManager.applyEvent(visitDay);

            GiveawayEventManager giveawayEventManager =
                    new GiveawayEventManager(benefitDetails, orderMenus);
            giveawayEventManager.applyEvent(visitDay);
        }

        OutputView.printGiveawayMenu(new GiveawayMenusDto(benefitDetails.getGiveawayMenus()));
        OutputView.printBenefitDetails(new BenefitDetailsDto(benefitDetails.getAppliedEvents()));
        OutputView.printTotalBenefitAmount(benefitDetails.calculateTotalBenefitAmount());

        ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator = new ExpectedPaymentAmountCalculator();
        OutputView.printExpectedPaymentAmountAfterDiscount(
                expectedPaymentAmountCalculator.calculateExpectedPaymentAmount(
                        orderMenus.calculateTotalOrderAmount(),
                        benefitDetails.calculateTotalBenefitAmount()
                )
        );

        EventBadgeAssigner eventBadgeAssigner = new EventBadgeAssigner();
        EventBadge eventBadge = eventBadgeAssigner.assignEventBadge(benefitDetails.calculateTotalBenefitAmount());
        OutputView.printDecemberEventBadge(eventBadge.toString());
    }

    private OrderMenus order() {
        return service.order(InputView.readOrderMenus());
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
