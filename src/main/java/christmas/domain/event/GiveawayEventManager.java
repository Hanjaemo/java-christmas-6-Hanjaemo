package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;

public class GiveawayEventManager implements EventManager {

    @Override
    public int applyEvent(EventContext eventContext, BenefitDetails benefitDetails) {
        int totalOrderAmount = eventContext.orderMenus().calculateTotalOrderAmount();
        if (totalOrderAmount < 120_000) {
            return 0;
        }
        eventContext.orderMenus().addOrderMenu(new OrderMenu(Menu.CHAMPAGNE, 1));
        benefitDetails.giveAwayMenu(Menu.CHAMPAGNE);
        int discount = Menu.CHAMPAGNE.getPrice();
        benefitDetails.addEvent(Event.GIVEAWAY, discount);
        return discount;
    }
}
