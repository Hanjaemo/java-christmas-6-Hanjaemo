package christmas.domain.event;

import christmas.CustomerInfo;
import christmas.domain.BenefitDetails;

public interface EventManager {

    int applyEvent(CustomerInfo customerInfo, BenefitDetails benefitDetails);
}
