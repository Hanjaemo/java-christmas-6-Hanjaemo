package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.VisitDay;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;

public class GiveawayEventManager extends EventManager {

    private final OrderMenus orderMenus;

    public GiveawayEventManager(BenefitDetails benefitDetails, OrderMenus orderMenus) {
        super(benefitDetails);
        this.orderMenus = orderMenus;
    }

    @Override
    public int applyEvent(VisitDay visitDay) {
        int totalOrderAmount = orderMenus.calculateTotalOrderAmount();
        if (totalOrderAmount < 120_000) {
            return 0;
        }
        orderMenus.addOrderMenu(new OrderMenu(Menu.CHAMPAGNE, 1));
        benefitDetails.giveAwayMenu(Menu.CHAMPAGNE);
        int discount = Menu.CHAMPAGNE.getPrice();
        benefitDetails.addEvent(Event.GIVEAWAY, discount);
        return discount;
    }
}
