package christmas.domain.event;

import christmas.domain.BenefitDetails;
import christmas.domain.VisitDay;

public abstract class EventManager {

    protected final BenefitDetails benefitDetails;

    public EventManager(BenefitDetails benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public abstract int applyEvent(VisitDay visitDay);
}
