package christmas.domain.event;

import christmas.CustomerInfo;
import christmas.domain.BenefitDetails;
import christmas.domain.SpecialDays;

public class SpecialDayDiscountEventManager implements EventManager {

    private static final int DISCOUNT_AMOUNT = 1_000;

    private final SpecialDays specialDays;

    public SpecialDayDiscountEventManager() {
        this.specialDays = new SpecialDays();
    }

    @Override
    public int applyEvent(CustomerInfo customerInfo, BenefitDetails benefitDetails) {
        if (specialDays.notContains(customerInfo.visitDay())) {
            return 0;
        }
        benefitDetails.addEvent(Event.SPECIAL_DISCOUNT, DISCOUNT_AMOUNT);
        return DISCOUNT_AMOUNT;
    }
}
