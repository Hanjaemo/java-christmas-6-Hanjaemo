package christmas.domain.event;

import christmas.CustomerInfo;
import christmas.domain.BenefitDetails;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;

public class GiveawayEventManager implements EventManager {

    private static final int MIN_TOTAL_ORDER_AMOUNT_FOR_APPLY_EVENT = 120_000;

    @Override
    public int applyEvent(CustomerInfo customerInfo, BenefitDetails benefitDetails) {
        int totalOrderAmount = customerInfo.orderMenus().calculateTotalOrderAmount();
        if (totalOrderAmount < MIN_TOTAL_ORDER_AMOUNT_FOR_APPLY_EVENT) {
            return 0;
        }
        customerInfo.orderMenus().addOrderMenu(new OrderMenu(Menu.CHAMPAGNE, 1));
        benefitDetails.giveAwayMenu(Menu.CHAMPAGNE);
        int discount = Menu.CHAMPAGNE.getPrice();
        benefitDetails.addEvent(Event.GIVEAWAY, discount);
        return discount;
    }
}
