package calculating;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationIncome {

    public BigDecimal calculateTotalAmount(double initialAmount, double annualInterest, int numberDays) {
        double percentagesDay = (initialAmount / 100 * annualInterest) / 365;
        initialAmount += percentagesDay * numberDays;

        BigDecimal result = new BigDecimal(initialAmount);
        result = result.setScale(2, RoundingMode.HALF_UP);
        return result;
    }
}
