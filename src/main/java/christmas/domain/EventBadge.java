package christmas.domain;

import java.util.function.Predicate;

public enum EventBadge {

    SANTA("산타", (amount) -> amount >= 20_000),
    TREE("트리", (amount) -> amount >= 10_000),
    STAR("별", (amount) -> amount >= 5_000),
    MISS("없음", (amount) -> amount < 5_000);

    private final String name;
    private final Predicate<Integer> totalBenefitAmountMatcher;

    EventBadge(String name, Predicate<Integer> totalBenefitAmountMatcher) {
        this.name = name;
        this.totalBenefitAmountMatcher = totalBenefitAmountMatcher;
    }

    public boolean matchesTotalBenefitAmount(int totalBenefitAmount) {
        return totalBenefitAmountMatcher.test(totalBenefitAmount);
    }

    @Override
    public String toString() {
        return name;
    }
}
