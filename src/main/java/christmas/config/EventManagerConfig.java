package christmas.config;

import christmas.domain.event.ChristmasDiscountEventManager;
import christmas.domain.event.EventManager;
import christmas.domain.event.GiveawayEventManager;
import christmas.domain.event.SpecialDayDiscountEventManager;
import christmas.domain.event.WeekDiscountEventManager;
import java.util.List;

public class EventManagerConfig {

    public List<EventManager> eventManagers() {
        return List.of(
                new ChristmasDiscountEventManager(),
                new WeekDiscountEventManager(),
                new SpecialDayDiscountEventManager(),
                new GiveawayEventManager()
        );
    }
}
