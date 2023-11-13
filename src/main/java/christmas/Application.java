package christmas;

import java.util.List;

import christmas.controller.Controller;
import christmas.domain.ExpectedPaymentAmountCalculator;
import christmas.domain.event.ChristmasDiscountEventManager;
import christmas.domain.event.EventBadgeAssigner;
import christmas.domain.event.GiveawayEventManager;
import christmas.domain.event.SpecialDayDiscountEventManager;
import christmas.domain.event.WeekDiscountEventManager;
import christmas.service.EventService;
import christmas.service.Service;

public class Application {
    public static void main(String[] args) {
        ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator = new ExpectedPaymentAmountCalculator();
        EventBadgeAssigner eventBadgeAssigner = new EventBadgeAssigner();
        Service service = new Service(expectedPaymentAmountCalculator, eventBadgeAssigner);
        EventService eventService = new EventService(
                List.of(new ChristmasDiscountEventManager(),
                        new WeekDiscountEventManager(),
                        new SpecialDayDiscountEventManager(),
                        new GiveawayEventManager())
        );
        Controller controller = new Controller(service, eventService);
        controller.run();
    }
}
