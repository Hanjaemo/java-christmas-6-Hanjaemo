package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.SpecialDays;

public class SpecialDayDiscountEventManager implements EventManager {

    private static final int DISCOUNT_AMOUNT = 1_000;

    private final SpecialDays specialDays;

    public SpecialDayDiscountEventManager() {
        this.specialDays = new SpecialDays();
    }

    @Override
    public int applyEvent(EventContext eventContext, BenefitDetails benefitDetails) {
        if (specialDays.notContains(eventContext.visitDay())) {
            return 0;
        }
        benefitDetails.addEvent(Event.SPECIAL_DISCOUNT, DISCOUNT_AMOUNT);
        return DISCOUNT_AMOUNT;
    }
}
