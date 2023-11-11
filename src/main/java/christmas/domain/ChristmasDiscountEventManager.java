package christmas.domain;

public class ChristmasDiscountEventManager {

    public int applyEvent(int visitDate, BenefitDetails benefitDetails) {
        if (visitDate < 1 || visitDate > 25) {
            return 0;
        }
        int discountAmount = 1000 + ((visitDate - 1) * 100);
        benefitDetails.addEvent(Event.CHRISTMAS_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
