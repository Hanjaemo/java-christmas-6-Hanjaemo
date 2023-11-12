package christmas.domain;

public class WeekDiscountEventManager extends EventManager {

    private final Order order;

    public WeekDiscountEventManager(BenefitDetails benefitDetails, Order order) {
        super(benefitDetails);
        this.order = order;
    }

    @Override
    public int applyEvent(VisitDay visitDay) {
        if (visitDay.isWeekend()) {
            int discountAmount = order.countMainMenus() * 2_023;
            benefitDetails.addEvent(Event.WEEKEND_DISCOUNT, discountAmount);
            return discountAmount;
        }
        int discountAmount = order.countDessertMenus() * 2_023;
        benefitDetails.addEvent(Event.WEEKDAY_DISCOUNT, discountAmount);
        return discountAmount;
    }
}
