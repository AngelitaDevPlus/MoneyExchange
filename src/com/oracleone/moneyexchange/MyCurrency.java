package com.oracleone.moneyexchange;

import java.util.Map;

public record MyCurrency(String base_code,
                         Map<String, Double> conversion_rates) {
}
