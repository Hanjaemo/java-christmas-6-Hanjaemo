package christmas.domain;

import java.util.EnumMap;
import java.util.Map;

public class BenefitDetails {

    private final Map<Event, Integer> appliedEvents;

    public BenefitDetails() {
        this.appliedEvents = new EnumMap<>(Event.class);
    }

    public void addEvent(Event event, int discountAmount) {
        appliedEvents.put(event, discountAmount);
    }
}

