package christmas.domain;

import java.util.Arrays;

public class EventBadgeAssigner {

    public EventBadge assignEventBadge(int totalBenefitAmount) {
        return Arrays.stream(EventBadge.values())
                .filter(eventBadge -> eventBadge.matchesTotalBenefitAmount(totalBenefitAmount))
                .findFirst()
                .orElse(EventBadge.MISS);
    }
}
