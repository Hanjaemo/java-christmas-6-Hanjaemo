package christmas.domain.event;

import christmas.domain.BenefitDetails;

public class WeekDiscountEventManager implements EventManager {

    private static final int DISCOUNT_PER_DESSERT = 2_023;
    private static final int DISCOUNT_PER_MAIN = 2_023;

    @Override
    public int applyEvent(EventContext eventContext, BenefitDetails benefitDetails) {
        if (eventContext.visitDay().isWeekend()) {
            return applyWeekendEvent(benefitDetails, eventContext);
        }
        return applyWeekdayEvent(benefitDetails, eventContext);
    }

    private int applyWeekdayEvent(BenefitDetails benefitDetails, EventContext eventContext) {
        int discountAmount = calculateDiscountAmount(eventContext);
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKDAY_DISCOUNT, discountAmount);
        return discountAmount;
    }

    private int calculateDiscountAmount(EventContext eventContext) {
        return eventContext.orderMenus().countDessertMenus() * DISCOUNT_PER_DESSERT;
    }

    private int applyWeekendEvent(BenefitDetails benefitDetails, EventContext eventContext) {
        int discountAmount = eventContext.orderMenus().countMainMenus() * DISCOUNT_PER_MAIN;
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKEND_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
