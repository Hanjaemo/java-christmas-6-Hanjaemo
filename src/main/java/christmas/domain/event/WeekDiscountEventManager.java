package christmas.domain.event;

import christmas.CustomerInfo;
import christmas.domain.BenefitDetails;

public class WeekDiscountEventManager implements EventManager {

    private static final int DISCOUNT_PER_DESSERT = 2_023;
    private static final int DISCOUNT_PER_MAIN = 2_023;

    @Override
    public int applyEvent(CustomerInfo customerInfo, BenefitDetails benefitDetails) {
        if (customerInfo.visitDay().isWeekend()) {
            return applyWeekendEvent(benefitDetails, customerInfo);
        }
        return applyWeekdayEvent(benefitDetails, customerInfo);
    }

    private int applyWeekdayEvent(BenefitDetails benefitDetails, CustomerInfo customerInfo) {
        int discountAmount = calculateDiscountAmount(customerInfo);
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKDAY_DISCOUNT, discountAmount);
        return discountAmount;
    }

    private int calculateDiscountAmount(CustomerInfo customerInfo) {
        return customerInfo.orderMenus().countDessertMenus() * DISCOUNT_PER_DESSERT;
    }

    private int applyWeekendEvent(BenefitDetails benefitDetails, CustomerInfo customerInfo) {
        int discountAmount = customerInfo.orderMenus().countMainMenus() * DISCOUNT_PER_MAIN;
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKEND_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
