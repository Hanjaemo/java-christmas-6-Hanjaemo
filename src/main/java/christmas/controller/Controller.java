package christmas.controller;

import christmas.CustomerInfo;
import christmas.domain.BenefitDetails;
import christmas.domain.VisitDay;
import christmas.domain.event.EventBadge;
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
        CustomerInfo customerInfo = createCustomerInfo();
        OutputView.printPreviewOfEventBenefitsMessage(customerInfo.visitDay().getVisitDay());
        OutputView.printOrderMenus(OrderMenusDto.from(customerInfo.orderMenus()));
        OutputView.printTotalOrderAmountBeforeDiscount(service.calculateTotalOrderAmount(customerInfo.orderMenus()));
        applyEvents(customerInfo);
    }

    private CustomerInfo createCustomerInfo() {
        VisitDay visitDay = repeatReadForInvalid(this::createVisitDay);
        OrderMenus orderMenus = repeatReadForInvalid(this::order);
        return new CustomerInfo(visitDay, orderMenus);
    }

    private <T> T repeatReadForInvalid(Supplier<T> reader) {
        try {
            return reader.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return repeatReadForInvalid(reader);
        }
    }

    private VisitDay createVisitDay() {
        return new VisitDay(InputView.readVisitDay());
    }

    private OrderMenus order() {
        return service.order(InputView.readOrderMenus());
    }

    private void applyEvents(CustomerInfo customerInfo) {
        BenefitDetails benefitDetails = eventService.applyEvents(
                customerInfo,
                new BenefitDetails()
        );

        OutputView.printGiveawayMenu(GiveawayMenusDto.from(benefitDetails.getGiveawayMenus()));
        OutputView.printBenefitDetails(BenefitDetailsDto.from(benefitDetails.getAppliedEvents()));
        OutputView.printTotalBenefitAmount(benefitDetails.calculateTotalBenefitAmount());

        int expectedPaymentAmount = service.calculateExpectedPaymentAmount(customerInfo.orderMenus(), benefitDetails);
        OutputView.printExpectedPaymentAmountAfterDiscount(expectedPaymentAmount);

        EventBadge eventBadge = service.assignEventBadge(benefitDetails);
        OutputView.printDecemberEventBadge(eventBadge.toString());
    }
}
