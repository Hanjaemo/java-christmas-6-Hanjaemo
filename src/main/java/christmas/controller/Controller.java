package christmas.controller;

import christmas.BenefitDetailsDto;
import christmas.GiveawayMenusDto;
import christmas.OrderMenusDto;
import christmas.domain.BenefitDetails;
import christmas.domain.ChristmasDiscountEventManager;
import christmas.domain.GiveawayEventManager;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.SpecialDayDiscountEventManager;
import christmas.domain.SpecialDays;
import christmas.domain.VisitDay;
import christmas.domain.WeekDiscountEventManager;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    public void run() {
        OutputView.printGreetingMessage();
        VisitDay visitDay = new VisitDay(InputView.readVisitDay());
        Map<Menu, Integer> orderMenus = InputView.readOrderMenus().entrySet().stream()
                .collect(Collectors.toMap(
                        menuInfo -> Menu.from(menuInfo.getKey()),
                        menuInfo -> menuInfo.getValue()));
        Order order = new Order(orderMenus);

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
    }
}
