package christmas.domain;

import java.util.EnumMap;
import java.util.Map;

import christmas.domain.event.Event;
import christmas.domain.menu.Menu;

public class BenefitDetails {

    private final Map<Event, Integer> appliedEvents;
    private final Map<Menu, Integer> giveawayMenus;

    public BenefitDetails() {
        this.appliedEvents = new EnumMap<>(Event.class);
        this.giveawayMenus =new EnumMap<>(Menu.class);
    }

    public void addEvent(Event event, int discountAmount) {
        appliedEvents.put(event, discountAmount);
    }

    public int calculateTotalBenefitAmount() {
        return appliedEvents.keySet()
                .stream()
                .mapToInt(appliedEvents::get)
                .sum();
    }

    public void giveAwayMenu(Menu menu) {
        giveawayMenus.put(menu, giveawayMenus.getOrDefault(menu, 0) + 1);
    }

    public Map<Event, Integer> getAppliedEvents() {
        return Map.copyOf(appliedEvents);
    }

    public Map<Menu, Integer> getGiveawayMenus() {
        return Map.copyOf(giveawayMenus);
    }
}

