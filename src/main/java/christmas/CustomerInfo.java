package christmas;

import christmas.domain.VisitDay;
import christmas.domain.order.OrderMenus;

public record CustomerInfo(VisitDay visitDay, OrderMenus orderMenus) {

}
