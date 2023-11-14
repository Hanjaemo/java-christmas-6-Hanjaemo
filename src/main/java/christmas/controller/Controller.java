package christmas.controller;

import christmas.domain.BenefitDetails;
import christmas.domain.VisitDay;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventContext;
import christmas.domain.order.OrderMenus;
import christmas.dto.BenefitDetailsDto;
import christmas.dto.GiveawayMenusDto;
import christmas.dto.OrderMenusDto;
import christmas.service.EventService;
import christmas.service.Service;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class Controller {

    private final Service service;
    private final EventService eventService;

    public Controller(Service service, EventService eventService) {
        this.service = service;
        this.eventService = eventService;
    }

    public void run() {
        OutputView.printGreetingMessage();
        VisitDay visitDay = repeatReadForInvalid(this::createVisitDay);
        OrderMenus orderMenus = repeatReadForInvalid(this::order);

        OutputView.printPreviewOfEventBenefitsMessage(visitDay.getVisitDay());

        OutputView.printOrderMenus(OrderMenusDto.from(orderMenus));
        OutputView.printTotalOrderAmountBeforeDiscount(service.calculateTotalOrderAmount(orderMenus));

        BenefitDetails benefitDetails = eventService.applyEvents(
                new EventContext(visitDay, orderMenus),
                new BenefitDetails()
        );

        OutputView.printGiveawayMenu(new GiveawayMenusDto(benefitDetails.getGiveawayMenus()));
        OutputView.printBenefitDetails(new BenefitDetailsDto(benefitDetails.getAppliedEvents()));
        OutputView.printTotalBenefitAmount(benefitDetails.calculateTotalBenefitAmount());

        int expectedPaymentAmount = service.calculateExpectedPaymentAmount(orderMenus, benefitDetails);
        OutputView.printExpectedPaymentAmountAfterDiscount(expectedPaymentAmount);

        EventBadge eventBadge = service.assignEventBadge(benefitDetails);
        OutputView.printDecemberEventBadge(eventBadge.toString());
    }

    private OrderMenus order() {
        return service.order(InputView.readOrderMenus());
    }

    private VisitDay createVisitDay() {
        return new VisitDay(InputView.readVisitDay());
    }

    private <T> T repeatReadForInvalid(Supplier<T> reader) {
        try {
            return reader.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return repeatReadForInvalid(reader);
        }
    }
}
