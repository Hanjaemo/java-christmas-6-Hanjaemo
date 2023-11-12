package christmas.controller;

import christmas.OrderMenusDto;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.VisitDay;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    public void run() {
        OutputView.printGreetingMessage();
        VisitDay visitDay = new VisitDay(InputView.readVisitDay());
        Map<Menu, Integer> orderMenus = InputView.readOrderMenus().entrySet().stream()
                .collect(Collectors.toMap(
                        menuInfo -> Menu.from(menuInfo.getKey()),
                        menuInfo -> menuInfo.getValue()));
        Order order = new Order(orderMenus);

        OutputView.printPreviewOfEventBenefitsMessage(visitDay.getVisitDay());

        OutputView.printOrderMenus(new OrderMenusDto(order.getOrderMenus()));
        OutputView.printTotalOrderAmountBeforeDiscount(order.calculateTotalOrderAmount());
    }
}
