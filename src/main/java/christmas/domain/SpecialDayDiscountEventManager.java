package christmas.domain;

public class SpecialDayDiscountEventManager extends EventManager {

    private static final int DISCOUNT_AMOUNT = 1_000;

    private final SpecialDays specialDays;

    public SpecialDayDiscountEventManager(BenefitDetails benefitDetails, SpecialDays specialDays) {
        super(benefitDetails);
        this.specialDays = specialDays;
    }

    @Override
    public int applyEvent(int visitDay) {
        if (!specialDays.contains(visitDay)) {
            return 0;
        }
        benefitDetails.addEvent(Event.SPECIAL_DISCOUNT, DISCOUNT_AMOUNT);
        return DISCOUNT_AMOUNT;
    }
}
