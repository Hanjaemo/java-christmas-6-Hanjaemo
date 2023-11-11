package christmas.domain;

public abstract class EventManager {

    protected final BenefitDetails benefitDetails;

    public EventManager(BenefitDetails benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public abstract int applyEvent(int visitDay);
}
