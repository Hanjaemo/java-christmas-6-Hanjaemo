package christmas.dto;

import christmas.domain.event.Event;
import java.util.Map;
import java.util.stream.Collectors;

public class BenefitDetailsDto {

    private final Map<String, Integer> benefitDetails;

    private BenefitDetailsDto(Map<Event, Integer> benefitDetails) {
        this.benefitDetails = benefitDetails.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        event -> event.getKey().getDescription(),
                        event -> event.getValue()
                ));
    }

    public static BenefitDetailsDto from(Map<Event, Integer> benefitDetails) {
        return new BenefitDetailsDto(benefitDetails);
    }

    public Map<String, Integer> getBenefitDetails() {
        return Map.copyOf(benefitDetails);
    }
}
