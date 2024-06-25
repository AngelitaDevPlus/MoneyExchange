package com.oracleone.moneyexchange;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencySearch currencySearch = new CurrencySearch();
        Menu menu = new Menu();
        boolean toExit = true;

        while (toExit) {
            menu.mainMenu();
            System.out.println("Enter INPUT currency type: ");
            var inputCurrencyType = String.valueOf(scanner.nextLine().toUpperCase());

            if(inputCurrencyType.equals("EXIT")) {
                toExit = false;
                break;
            }

            System.out.println("Enter OUTPUT currency type: ");
            var outputCurrencyType = String.valueOf(scanner.nextLine()).toUpperCase();

            if (outputCurrencyType.equals("EXIT")) {
                toExit = false;
                break;
            }

            if (inputCurrencyType.isEmpty() || outputCurrencyType.isEmpty()
                    || inputCurrencyType.length() != 3 || outputCurrencyType.length() != 3) {
                throw new IllegalArgumentException("Input not valid, enter 3 character currency input");
            }

            MyCurrency myCurrency = currencySearch.searchCurrency(inputCurrencyType, outputCurrencyType);
            System.out.println("The currency type search is " + myCurrency.base_code());

            System.out.println("Enter the amount to exchange: ");
            var amountToExchange = scanner.nextDouble();

            if (amountToExchange <= 0) {
                throw new IllegalArgumentException("Enter an amount difference than zero:  ");
            }

            Calculation calculation = new Calculation();
            calculation.performCalculation(myCurrency, outputCurrencyType, amountToExchange);
            scanner.nextLine();
        }
        System.out.println("Thank you for using our service. The program has ended.");
    }
}
