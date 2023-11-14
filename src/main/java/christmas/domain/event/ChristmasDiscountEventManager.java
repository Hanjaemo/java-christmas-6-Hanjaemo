package christmas.domain.event;

import christmas.domain.BenefitDetails;

public class ChristmasDiscountEventManager implements EventManager {

    private static final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
    private static final int DAILY_INCREMENT_AMOUNT = 100;

    @Override
    public int applyEvent(EventContext eventContext, BenefitDetails benefitDetails) {
        if (eventContext.visitDay().isNotWithinChristmasPeriod()) {
            return 0;
        }
        int discountAmount = calculateDiscount(eventContext);
        benefitDetails.addEvent(Event.CHRISTMAS_DISCOUNT, discountAmount);
        return discountAmount;
    }

    private int calculateDiscount(EventContext eventContext) {
        return DEFAULT_DISCOUNT_AMOUNT + (eventContext.visitDay().decreaseOneDay() * DAILY_INCREMENT_AMOUNT);
    }
}
