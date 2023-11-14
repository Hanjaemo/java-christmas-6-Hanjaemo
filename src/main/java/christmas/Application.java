package christmas;

import christmas.controller.Controller;
import christmas.domain.ExpectedPaymentAmountCalculator;
import christmas.domain.event.ChristmasDiscountEventManager;
import christmas.domain.event.EventBadgeAssigner;
import christmas.domain.event.GiveawayEventManager;
import christmas.domain.event.SpecialDayDiscountEventManager;
import christmas.domain.event.WeekDiscountEventManager;
import christmas.service.EventService;
import christmas.service.Service;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Service service = createService();
        EventService eventService = createEventService();
        Controller controller = new Controller(service, eventService);
        controller.run();
    }

    private static Service createService() {
        ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator = new ExpectedPaymentAmountCalculator();
        EventBadgeAssigner eventBadgeAssigner = new EventBadgeAssigner();
        return new Service(expectedPaymentAmountCalculator, eventBadgeAssigner);
    }

    private static EventService createEventService() {
        return new EventService(
                List.of(new ChristmasDiscountEventManager(),
                        new WeekDiscountEventManager(),
                        new SpecialDayDiscountEventManager(),
                        new GiveawayEventManager())
        );
    }
}
