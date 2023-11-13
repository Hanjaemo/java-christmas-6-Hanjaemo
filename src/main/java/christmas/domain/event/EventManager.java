package christmas.domain.event;

import christmas.domain.BenefitDetails;

public interface EventManager {

    int applyEvent(EventContext eventContext, BenefitDetails benefitDetails);
}
