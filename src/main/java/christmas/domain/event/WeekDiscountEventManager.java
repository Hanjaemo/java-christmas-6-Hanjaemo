package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.VisitDay;
import christmas.domain.order.OrderMenus;

public class WeekDiscountEventManager extends EventManager {

    private final OrderMenus orderMenus;

    public WeekDiscountEventManager(BenefitDetails benefitDetails, OrderMenus orderMenus) {
        super(benefitDetails);
        this.orderMenus = orderMenus;
    }

    @Override
    public int applyEvent(VisitDay visitDay) {
        if (visitDay.isWeekend()) {
            return applyWeekendEvent();
        }
        return applyWeekdayEvent();
    }

    private int applyWeekdayEvent() {
        int discountAmount = orderMenus.countDessertMenus() * 2_023;
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKDAY_DISCOUNT, discountAmount);
        return discountAmount;
    }

    private int applyWeekendEvent() {
        int discountAmount = orderMenus.countMainMenus() * 2_023;
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKEND_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
