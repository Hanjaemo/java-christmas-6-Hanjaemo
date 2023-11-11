package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDiscountEventManager extends EventManager {

    private final Order order;

    public WeekDiscountEventManager(BenefitDetails benefitDetails, Order order) {
        super(benefitDetails);
        this.order = order;
    }

    @Override
    public int applyEvent(int visitDay) {
        DayOfWeek visitDayOfWeek = LocalDate.of(2023, 12, visitDay).getDayOfWeek();
        if (visitDayOfWeek == DayOfWeek.FRIDAY || visitDayOfWeek == DayOfWeek.SATURDAY) {
            int discountAmount = order.countMainMenus() * 2_023;
            benefitDetails.addEvent(Event.WEEKEND_DISCOUNT, discountAmount);
            return discountAmount;
        }
        int discountAmount = order.countDessertMenus() * 2_023;
        benefitDetails.addEvent(Event.WEEKDAY_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
