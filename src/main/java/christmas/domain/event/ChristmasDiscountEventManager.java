package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.VisitDay;

public class ChristmasDiscountEventManager extends EventManager {

    private static final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
    private static final int DAILY_INCREMENT_AMOUNT = 100;

    public ChristmasDiscountEventManager(BenefitDetails benefitDetails) {
        super(benefitDetails);
    }

    @Override
    public int applyEvent(VisitDay visitDay) {
        if (visitDay.isNotWithinChristmasPeriod()) {
            return 0;
        }
        int discountAmount = DEFAULT_DISCOUNT_AMOUNT + (visitDay.decreaseOneDay() * DAILY_INCREMENT_AMOUNT);
        benefitDetails.addEvent(Event.CHRISTMAS_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
