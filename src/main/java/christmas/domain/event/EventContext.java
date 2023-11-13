package christmas.domain.event;

import christmas.domain.VisitDay;
import christmas.domain.order.OrderMenus;

public record EventContext(VisitDay visitDay, OrderMenus orderMenus) {

}
