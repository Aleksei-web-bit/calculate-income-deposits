package controller;

import calculating.CalculationIncome;
import calculating.CalculationIncomeCapitalization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    /**
     * Данный метод возвращает результат вычисления итоговой суммы на счете клиента без учета капитализации.
     */
    @PostMapping("/post-amount-revenue")
    public ResponseEntity postAmountRevenue(HttpServletRequest request) {
        CalculationIncome obj = new CalculationIncome();

        String initialAmount = request.getParameter("initial-amount");
        String annualInterest = request.getParameter("annual-interest");
        String numberDays = request.getParameter("number-days");

        double doubleInitialAmount = Double.parseDouble(initialAmount);
        double doubleAnnualInterest = Double.parseDouble(annualInterest);
        int intNumberDays = Integer.parseInt(numberDays);

        System.out.println("Первоначальная сумма: " + doubleInitialAmount);
        System.out.println("Годовой процент: " + doubleAnnualInterest);
        System.out.println("Срок вклада: " + intNumberDays);

        System.out.println("Результат работы вычислительного метода = "
                + obj.calculateTotalAmount(doubleInitialAmount, doubleAnnualInterest, intNumberDays));

        return ResponseEntity.ok(obj.calculateTotalAmount(doubleInitialAmount, doubleAnnualInterest, intNumberDays));
    }

    /**
     * Данный метод возвращает результат вычисления итоговой суммы на счете клиента с учетом капитализации.
     */
    @PostMapping("/post-amount-revenue-capitalization")
    public ResponseEntity postAmountRevenueCapitalization(HttpServletRequest request) {
        CalculationIncomeCapitalization obj = new CalculationIncomeCapitalization();

        String initialAmount = request.getParameter("initial-amount");
        String annualInterest = request.getParameter("annual-interest");
        String numberDays = request.getParameter("number-days");
        String daysReplenish = request.getParameter("days-replenish");
        String amountReplenish = request.getParameter("amount-replenish");

        String[] strDaysReplenish = daysReplenish.split(" ");
        String[] strAmountReplenish = amountReplenish.split(" ");

        double doubleInitialAmount = Double.parseDouble(initialAmount);
        double doubleAnnualInterest = Double.parseDouble(annualInterest);
        int intNumberDays = Integer.parseInt(numberDays);

        int[] intDaysReplenish = new int[strDaysReplenish.length];
        for (int i = 0; i < intDaysReplenish.length; i++) {
            intDaysReplenish[i] = Integer.parseInt(strDaysReplenish[i]);
            System.out.println("День пополнения счёта: " + intDaysReplenish[i]);
        }

        double[] doubleAmountReplenish = new double[strAmountReplenish.length];
        for (int j = 0; j < doubleAmountReplenish.length; j++) {
            doubleAmountReplenish[j] = Double.parseDouble(strAmountReplenish[j]);
            System.out.println("Сумма пополнения счёта: " + doubleAmountReplenish[j]);
        }

        System.out.println("Первоначальная сумма: " + doubleInitialAmount);
        System.out.println("Годовой процент: " + doubleAnnualInterest);
        System.out.println("Срок вклада: " + intNumberDays);

        Map<Integer, Double> dayAndAmount = new HashMap<Integer, Double>();

        for (int k = 0; k < intDaysReplenish.length; k++) {
            dayAndAmount.put(intDaysReplenish[k], doubleAmountReplenish[k]);
        }

        System.out.println("Содержимое Map: " + dayAndAmount);

        System.out.println("Результат работы вычислительного метода: "
                + obj.calculateTotalAmount(1000000, 10, 180, dayAndAmount));

        return ResponseEntity.ok(obj.calculateTotalAmount(doubleInitialAmount, doubleAnnualInterest, intNumberDays, dayAndAmount));
    }
}
