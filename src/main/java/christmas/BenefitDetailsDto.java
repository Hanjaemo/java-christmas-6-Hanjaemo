package christmas;

import java.util.Map;

public class BenefitDetailsDto {

    private final Map<String, Integer> benefitDetails;

    public BenefitDetailsDto(Map<String, Integer> benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public Map<String, Integer> getBenefitDetails() {
        return Map.copyOf(benefitDetails);
    }
}
