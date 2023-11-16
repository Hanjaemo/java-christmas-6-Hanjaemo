package christmas.domain.event;

import christmas.CustomerInfo;
import christmas.domain.BenefitDetails;

public class ChristmasDiscountEventManager implements EventManager {

    private static final int CHRISTMAS_D_DAY = 25;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
    private static final int DAILY_INCREMENT_AMOUNT = 100;

    @Override
    public int applyEvent(CustomerInfo customerInfo, BenefitDetails benefitDetails) {
        if (customerInfo.visitDay().isNotWithinChristmasPeriod(CHRISTMAS_D_DAY)) {
            return 0;
        }
        int discountAmount = calculateDiscount(customerInfo);
        benefitDetails.addEvent(Event.CHRISTMAS_DISCOUNT, discountAmount);
        return discountAmount;
    }

    private int calculateDiscount(CustomerInfo customerInfo) {
        return DEFAULT_DISCOUNT_AMOUNT + (customerInfo.visitDay().decreaseOneDay() * DAILY_INCREMENT_AMOUNT);
    }
}
