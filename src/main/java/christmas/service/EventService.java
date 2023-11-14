package christmas.service;

import christmas.domain.BenefitDetails;
import christmas.domain.event.EventContext;
import christmas.domain.event.EventManager;
import java.util.List;

public class EventService {

    private static final int MIN_TOTAL_ORDER_AMOUNT_FOR_APPLY_EVENT = 10_000;

    private final List<EventManager> eventManagers;

    public EventService(List<EventManager> eventManagers) {
        this.eventManagers = eventManagers;
    }

    public BenefitDetails applyEvents(EventContext eventContext, BenefitDetails benefitDetails) {
        if (eventContext.orderMenus().calculateTotalOrderAmount() >= MIN_TOTAL_ORDER_AMOUNT_FOR_APPLY_EVENT) {
            eventManagers.forEach(eventManager -> eventManager.applyEvent(eventContext, benefitDetails));
        }
        return benefitDetails;
    }
}
