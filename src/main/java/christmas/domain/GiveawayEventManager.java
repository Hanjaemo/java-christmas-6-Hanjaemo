package christmas.domain;

public class GiveawayEventManager extends EventManager {

    private final Order order;

    public GiveawayEventManager(BenefitDetails benefitDetails, Order order) {
        super(benefitDetails);
        this.order = order;
    }

    @Override
    public int applyEvent(VisitDay visitDay) {
        int totalOrderAmount = order.calculateTotalOrderAmount();
        if (totalOrderAmount < 120_000) {
            return 0;
        }
        order.addOrderMenu(new OrderMenu(Menu.CHAMPAGNE, 1));
        benefitDetails.giveAwayMenu(Menu.CHAMPAGNE);
        int discount = Menu.CHAMPAGNE.getPrice();
        benefitDetails.addEvent(Event.GIVEAWAY, discount);
        return discount;
    }
}
