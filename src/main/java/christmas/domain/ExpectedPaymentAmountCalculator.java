package christmas.domain;

public class ExpectedPaymentAmountCalculator {

    public int calculateExpectedPaymentAmount(int totalOrderAmount, int totalBenefitAmount) {
        return totalOrderAmount - totalBenefitAmount;
    }
}
