package christmas.domain.event;

import christmas.domain.BenefitDetails;

public class WeekDiscountEventManager implements EventManager {

    @Override
    public int applyEvent(EventContext eventContext, BenefitDetails benefitDetails) {
        if (eventContext.visitDay().isWeekend()) {
            return applyWeekendEvent(benefitDetails, eventContext);
        }
        return applyWeekdayEvent(benefitDetails, eventContext);
    }

    private int applyWeekdayEvent(BenefitDetails benefitDetails, EventContext eventContext) {
        int discountAmount = eventContext.orderMenus().countDessertMenus() * 2_023;
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKDAY_DISCOUNT, discountAmount);
        return discountAmount;
    }

    private int applyWeekendEvent(BenefitDetails benefitDetails, EventContext eventContext) {
        int discountAmount = eventContext.orderMenus().countMainMenus() * 2_023;
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKEND_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
