package com.oracleone.moneyexchange;

import java.text.DecimalFormat;
import java.util.Map;

public class Calculation {
    public void performCalculation(MyCurrency myCurrency, String outputCurrencyType, double amountToExchange) {
        DecimalFormat df = new DecimalFormat("#.#####");
        Map<String, Double> conversion_rates = myCurrency.conversion_rates();
        double amountResult = amountToExchange
                * conversion_rates.get(outputCurrencyType);
        System.out.println("The resulting amount is " + df.format(amountResult) + " "
                + outputCurrencyType + ".");
    }
}
