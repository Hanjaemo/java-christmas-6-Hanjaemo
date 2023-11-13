package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.order.Order;
import christmas.domain.VisitDay;

public class WeekDiscountEventManager extends EventManager {

    private final Order order;

    public WeekDiscountEventManager(BenefitDetails benefitDetails, Order order) {
        super(benefitDetails);
        this.order = order;
    }

    @Override
    public int applyEvent(VisitDay visitDay) {
        if (visitDay.isWeekend()) {
            return applyWeekendEvent();
        }
        return applyWeekdayEvent();
    }

    private int applyWeekdayEvent() {
        int discountAmount = order.countDessertMenus() * 2_023;
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKDAY_DISCOUNT, discountAmount);
        return discountAmount;
    }

    private int applyWeekendEvent() {
        int discountAmount = order.countMainMenus() * 2_023;
        if (discountAmount == 0) {
            return discountAmount;
        }
        benefitDetails.addEvent(Event.WEEKEND_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
