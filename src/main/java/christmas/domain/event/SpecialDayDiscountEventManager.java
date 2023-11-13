package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.SpecialDays;
import christmas.domain.VisitDay;

public class SpecialDayDiscountEventManager extends EventManager {

    private static final int DISCOUNT_AMOUNT = 1_000;

    private final SpecialDays specialDays;

    public SpecialDayDiscountEventManager(BenefitDetails benefitDetails, SpecialDays specialDays) {
        super(benefitDetails);
        this.specialDays = specialDays;
    }

    @Override
    public int applyEvent(VisitDay visitDay) {
        if (specialDays.notContains(visitDay)) {
            return 0;
        }
        benefitDetails.addEvent(Event.SPECIAL_DISCOUNT, DISCOUNT_AMOUNT);
        return DISCOUNT_AMOUNT;
    }
}
