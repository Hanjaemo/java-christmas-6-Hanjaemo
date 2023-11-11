package christmas.domain;

public class ChristmasDiscountEventManager extends EventManager {

    public ChristmasDiscountEventManager(BenefitDetails benefitDetails) {
        super(benefitDetails);
    }

    @Override
    public int applyEvent(int visitDay) {
        if (visitDay < 1 || visitDay > 25) {
            return 0;
        }
        int discountAmount = 1000 + ((visitDay - 1) * 100);
        benefitDetails.addEvent(Event.CHRISTMAS_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
