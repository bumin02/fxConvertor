package CC_04_Wed_16_Frank_Group;

import java.util.HashMap;
import java.util.*;


public class CurrencyTable {

    int TABLE_SIZE = 4;

    public static Currency findCurrency(String name, List<Currency> currencies) {
        for (Currency a : currencies) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public void display(DataBase database) {
        
        List<Currency> currencies = database.currencies.get(database.findMostRecentDate());

        // Find all the popular currencies and store in list
        List<String> popularCurrencies = new ArrayList<>();
        for (Currency a : currencies) {
            if (a.isPopular()) {
                popularCurrencies.add(a.getName().toUpperCase());
            }
        }

        HashMap<String, Double> pop1 = findCurrency(popularCurrencies.get(0), currencies).getConversionRates();
        HashMap<String, Double> pop2 = findCurrency(popularCurrencies.get(1), currencies).getConversionRates();
        HashMap<String, Double> pop3 = findCurrency(popularCurrencies.get(2), currencies).getConversionRates();
        HashMap<String, Double> pop4 = findCurrency(popularCurrencies.get(3), currencies).getConversionRates();

        // todo: limit floats to 6 DIGITS in total, not 6 DECIMAL PLACES

        System.out.println("_____________________________________________________________________");

        System.out.printf("|    %6s    |  %6s    |  %6s    |  %6s    |  %6s    |\n", "From/To", popularCurrencies.get(0), popularCurrencies.get(1), popularCurrencies.get(2), popularCurrencies.get(3));
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("|               |            |            |            |            |\n");
        System.out.printf("|  %6s       | %6s     |  %.6f  |  %.6f  |  %.6f  |\n", popularCurrencies.get(0), "-", pop1.get(popularCurrencies.get(1)), pop1.get(popularCurrencies.get(2)), pop1.get(popularCurrencies.get(3)));
        System.out.printf("|               |            |            |            |            |\n");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("|               |            |            |            |            |\n");
        System.out.printf("|  %6s       |  %6f  | %6s     |  %6f  |  %6f  |\n", popularCurrencies.get(1), pop2.get(popularCurrencies.get(0)), "-", pop2.get(popularCurrencies.get(2)), pop2.get(popularCurrencies.get(3)));
        System.out.printf("|               |            |            |            |            |\n");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("|               |            |            |            |            |\n");
        System.out.printf("|  %6s       |  %6f  |  %6f  | %6s     |  %6f  |\n", popularCurrencies.get(2), pop3.get(popularCurrencies.get(0)), pop3.get(popularCurrencies.get(1)), "-", pop3.get(popularCurrencies.get(3)));
        System.out.printf("|               |            |            |            |            |\n"); 
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("|               |            |            |            |            |\n");
        System.out.printf("|  %6s       |  %6f  |  %6f  |  %6f  | %6s     |\n", popularCurrencies.get(3), pop4.get(popularCurrencies.get(0)), pop4.get(popularCurrencies.get(1)), pop4.get(popularCurrencies.get(2)), "-");
        System.out.printf("|               |            |            |            |            |\n");
        System.out.println("---------------------------------------------------------------------");

    }
}