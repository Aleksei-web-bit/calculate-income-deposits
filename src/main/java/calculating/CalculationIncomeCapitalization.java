package calculating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CalculationIncomeCapitalization {

    public BigDecimal calculateTotalAmount(double initialAmount, double annualInterest, int numberDays,
                                           Map<Integer, Double> dayAndAmount) {
        for (int i = 1; i <= numberDays; i++) {
            double percentagesDay = (initialAmount / 100 * annualInterest) / 365;
            initialAmount += percentagesDay;

            if (dayAndAmount.containsKey(i)) {
                initialAmount += dayAndAmount.get(i);
            }
        }

        BigDecimal result = new BigDecimal(initialAmount);
        result = result.setScale(2, RoundingMode.HALF_UP);
        return result;
    }
}
