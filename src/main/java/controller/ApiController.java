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

    @GetMapping("/home-page")
    public ResponseEntity getHomePage() {
        return ResponseEntity.ok("<html> <head> <link href=\"home-page.css\" rel=\"stylesheet\"></head> <body> <h1>Расчёт доходов по банковским вкладам</h1> <p>Выберите тип банковского вклада:</p> <a href=\"calculate-amount-revenue\">Вклад без капитализации</a> <a href=\"calculate-amount-revenue-capitalization\">Вклад с капитализацией</a></body></html>");
    }

    @GetMapping("/calculate-amount-revenue")
    public ResponseEntity getAmountRevenue() {
        return ResponseEntity.ok("<html>\r\n" + "<head>\r\n"
                + "<link href=\"calculate-amount-revenue.css\" rel=\"stylesheet\">\r\n" + "</head>\r\n" + "<body>\r\n"
                + "<form action=\"/post-amount-revenue\" method=\"post\">\r\n"
                + "<h1>Расчёт доходов по банковским вкладам</h1>\r\n" + "<section>\r\n"
                + "<h2>Параметры банковского вклада</h2>\r\n" + "<p>\r\n" + "<label for=\"initial_amount\">\r\n"
                + "<span>Первоначальная сумма:</span>\r\n" + "<strong><abbr title=\"required\">*</abbr></strong>\r\n"
                + "</label>\r\n"
                + "<input type=\"initial_amount\" id=\"initial_amount\" name=\"initial-amount\">\r\n" + "</p>\r\n"
                + "<p>\r\n" + "<label for=\"annual_interest\">\r\n" + "<span>Годовой процент:</span>\r\n"
                + "<strong><abbr title=\"required\">*</abbr></strong>\r\n" + "</label>\r\n"
                + "<input type=\"annual_interest\" id=\"annual_interest\" name=\"annual-interest\">\r\n" + "</p>\r\n"
                + "<p>\r\n" + "<label for=\"number_days\">\r\n" + "<span>Срок вклада (в сутках):</span>\r\n"
                + "<strong><abbr title=\"required\">*</abbr></strong>\r\n" + "</label>\r\n"
                + "<input type=\"number_days\" id=\"number_days\" name=\"number-days\">\r\n" + "</p>\r\n"
                + "</section>\r\n" + "<p> <button type=\"submit\">Вычислить</button> </p>\r\n" + "</form>\r\n"
                + "</body>\r\n" + "</html>\r\n" + "");
    }

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

        return ResponseEntity.ok("Итоговая сумма на банковском счёте при заданных условиях составит: "
                + obj.calculateTotalAmount(doubleInitialAmount, doubleAnnualInterest, intNumberDays) + "<a href=\"home-page\">Вернуться на главную страницу</a>");
    }

    @GetMapping("/calculate-amount-revenue-capitalization")
    public ResponseEntity getAmountRevenueCapitalization() {
        return ResponseEntity.ok("<html>\r\n" + "<head>\r\n"
                + "<link href=\"calculate-amount-revenue-capitalization.css\" rel=\"stylesheet\">\r\n" + "</head>\r\n"
                + "<body>\r\n" + "<form action=\"/post-amount-revenue-capitalization\" method=\"post\">\r\n"
                + "<h1>Расчёт доходов по банковским вкладам</h1>\r\n" + "<section>\r\n"
                + "<h2>Параметры банковского вклада</h2>\r\n" + "<p>\r\n" + "<label for=\"initial_amount\">\r\n"
                + "<span>Первоначальная сумма:</span>\r\n" + "<strong><abbr title=\"required\">*</abbr></strong>\r\n"
                + "</label>\r\n"
                + "<input type=\"initial_amount\" id=\"initial_amount\" name=\"initial-amount\">\r\n" + "</p>\r\n"
                + "<p>\r\n" + "<label for=\"annual_interest\">\r\n" + "<span>Годовой процент:</span>\r\n"
                + "<strong><abbr title=\"required\">*</abbr></strong>\r\n" + "</label>\r\n"
                + "<input type=\"annual_interest\" id=\"annual_interest\" name=\"annual-interest\">\r\n" + "</p>\r\n"
                + "<p>\r\n" + "<label for=\"number_days\">\r\n" + "<span>Срок вклада (в сутках):</span>\r\n"
                + "<strong><abbr title=\"required\">*</abbr></strong>\r\n" + "</label>\r\n"
                + "<input type=\"number_days\" id=\"number_days\" name=\"number-days\">\r\n" + "</p>\r\n" + "<p>\r\n"
                + "<label for=\"days_replenish\">\r\n" + "<span>Дни пополнения счёта (укажите через пробел):</span>\r\n"
                + "<strong><abbr title=\"required\">*</abbr></strong>\r\n" + "</label>\r\n"
                + "<input type=\"days_replenish\" id=\"days_replenish\" name=\"days-replenish\">\r\n" + "</p>\r\n"
                + "<p>\r\n" + "<label for=\"amount_replenish\">\r\n"
                + "<span>Суммы пополнения счёта (укажите через пробел):</span>\r\n"
                + "<strong><abbr title=\"required\">*</abbr></strong>\r\n" + "</label>\r\n"
                + "<input type=\"amount_replenish\" id=\"amount_replenish\" name=\"amount-replenish\">\r\n" + "</p>\r\n"
                + "</section>\r\n" + "<p> <button type=\"submit\">Вычислить</button> </p>\r\n" + "</form>\r\n"
                + "</body>\r\n" + "</html>");
    }

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

        return ResponseEntity.ok("Итоговая сумма на банковском счёте при заданных условиях составит: " + obj
                .calculateTotalAmount(doubleInitialAmount, doubleAnnualInterest, intNumberDays, dayAndAmount) + "<a href=\"home-page\">Вернуться на главную страницу</a>");
    }
}
