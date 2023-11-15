package christmas;

import christmas.config.EventManagerConfig;
import christmas.controller.Controller;
import christmas.domain.ExpectedPaymentAmountCalculator;
import christmas.domain.event.EventBadgeAssigner;
import christmas.service.EventService;
import christmas.service.Service;

public class Application {
    public static void main(String[] args) {
        EventManagerConfig eventManagerConfig = new EventManagerConfig();

        Service service = createService();
        EventService eventService = new EventService(eventManagerConfig.eventManagers());

        Controller controller = new Controller(service, eventService);
        controller.run();
    }

    private static Service createService() {
        ExpectedPaymentAmountCalculator expectedPaymentAmountCalculator = new ExpectedPaymentAmountCalculator();
        EventBadgeAssigner eventBadgeAssigner = new EventBadgeAssigner();
        return new Service(expectedPaymentAmountCalculator, eventBadgeAssigner);
    }
}
