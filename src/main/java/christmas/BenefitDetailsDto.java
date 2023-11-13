package christmas;

import java.util.Map;
import java.util.stream.Collectors;

import christmas.domain.event.Event;

public class BenefitDetailsDto {

    private final Map<String, Integer> benefitDetails;

    public BenefitDetailsDto(Map<Event, Integer> benefitDetails) {
        this.benefitDetails = benefitDetails.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        event -> event.getKey().getDescription(),
                        event -> event.getValue()
                ));
    }

    public Map<String, Integer> getBenefitDetails() {
        return Map.copyOf(benefitDetails);
    }
}
