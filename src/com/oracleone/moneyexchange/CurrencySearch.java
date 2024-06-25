package com.oracleone.moneyexchange;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencySearch {
    public MyCurrency searchCurrency(String InputCurrency, String OutputCurrency) {
        Scanner scanner = new Scanner(System.in);

        var keyAPI = "393e125122963b087db5c997";
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + keyAPI + "/latest/" + InputCurrency + "/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri).build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String jSonResponse = response.body();

            MyCurrency currencyAsRecordObject = new Gson().fromJson(jSonResponse, MyCurrency.class);

            // Getting the input currency attribute through the Record class object
            String currencyRecordObjectAttribute = currencyAsRecordObject.base_code();

            // Getting the output currency attribute through a Json Object class with the Gson Library
            JsonObject jsonObject = new Gson().fromJson(jSonResponse, JsonObject.class);
            JsonObject exchangeRates = jsonObject.getAsJsonObject("conversion_rates");

            double exchangeRateJsonObjectAttribute = exchangeRates.get(OutputCurrency).getAsDouble();

            //double roundedValue = roundToThreeDecimalPlaces(result);
            // Using DecimalFormat class to print out a specific number of decimals
            DecimalFormat df = new DecimalFormat("#.#####");

            // Printing the information input the program received
            System.out.println("Exchange from " + currencyRecordObjectAttribute + " to "
                    + OutputCurrency + " at the rate of " + df.format(exchangeRateJsonObjectAttribute));

            return currencyAsRecordObject;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Not found :( " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("General Exception" + e.getMessage());
        }
    }
    /*
    double roundToThreeDecimalPlaces (double value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        bigDecimal = bigDecimal.setScale(5, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
     */
}