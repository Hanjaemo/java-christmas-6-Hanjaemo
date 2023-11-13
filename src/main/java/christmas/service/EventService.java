package christmas.service;

import java.util.List;

import christmas.domain.BenefitDetails;
import christmas.domain.event.EventContext;
import christmas.domain.event.EventManager;

public class EventService {

    private final List<EventManager> eventManagers;

    public EventService(List<EventManager> eventManagers) {
        this.eventManagers = eventManagers;
    }

    public BenefitDetails applyEvents(EventContext eventContext, BenefitDetails benefitDetails) {
        if (eventContext.orderMenus().calculateTotalOrderAmount() >= 10000) {
            eventManagers.forEach(eventManager -> eventManager.applyEvent(eventContext, benefitDetails));
        }
        return benefitDetails;
    }
}
