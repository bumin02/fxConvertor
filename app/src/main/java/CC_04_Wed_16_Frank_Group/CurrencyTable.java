package CC_04_Wed_16_Frank_Group;

import java.util.List;

public class CurrencyTable {

        int TABLE_SIZE = 4;
        DataBase db;

        public CurrencyTable(DataBase db) {
                this.db = db;
        }

        public static Currency findCurrency(String name, List<Currency> currencies) {
                for (Currency a : currencies) {
                        if (a.getName().equals(name)) {
                                return a;
                        }
                }
                return null;
        }

        public String displayText(List<Currency> oldCurrencies, List<Currency> newCurrencies, String codeFrom,
                        String codeTo) {

                // find the exchange rate from codeFrom to codeTo from the newCurrencies
                Currency newCurrencyFrom = findCurrency(codeFrom, newCurrencies);

                double newExchangeRate = newCurrencyFrom.getConversionRates().get(codeTo);

                // find the exchange rate from codeFrom to codeTo from the oldCurrencies
                Currency oldCurrencyFrom = findCurrency(codeFrom, oldCurrencies);
                if (oldCurrencyFrom == null) {
                        if (String.valueOf(newExchangeRate).length() > 6) {
                                return String.valueOf(newExchangeRate).substring(0, 8);
                        } else {
                                return String.valueOf(newExchangeRate);
                        }
                }

                if (oldCurrencyFrom.getConversionRates().get(codeTo) == null) {
                        if (String.valueOf(newExchangeRate).length() > 6) {
                                return String.valueOf(newExchangeRate).substring(0, 8);
                        } else {
                                return String.valueOf(newExchangeRate);
                        }
                }

                double oldExchangeRate = oldCurrencyFrom.getConversionRates().get(codeTo);

                if (oldExchangeRate < newExchangeRate) {
                        if (String.valueOf(newExchangeRate).length() > 6) {
                                return (String.valueOf(newExchangeRate).substring(0, 7) + " (D)");
                        } else {
                                return (String.valueOf(newExchangeRate) + " (D)");
                        }
                } else if (oldExchangeRate > newExchangeRate) {
                        if (String.valueOf(newExchangeRate).length() > 6) {
                                return (String.valueOf(newExchangeRate).substring(0, 7) + " (U)");
                        } else {
                                return (String.valueOf(newExchangeRate) + " (U)");
                        }
                } else {
                        if (String.valueOf(newExchangeRate).length() > 6) {
                                return String.valueOf(newExchangeRate).substring(0, 7);
                        } else {
                                return String.valueOf(newExchangeRate);
                        }
                }

        }

        public void display(DataBase database) {

                List<Currency> currencies = database.currencies.get(database.findMostRecentDate());
                List<Currency> oldCurrencies = database.currencies.get(database.findSecondMostRecentDate());

                // Find all the popular currencies and store in list
                List<String> popularCurrencies = this.db.findPopularCurrencies();

                System.out.println(
                                "_____________________________________________________________________________________________________________");

                System.out.printf(
                                "|    %6s    |       %6s         |       %6s         |       %6s         |       %6s         |\n",
                                "From/To",
                                popularCurrencies.get(0), popularCurrencies.get(1), popularCurrencies.get(2),
                                popularCurrencies.get(3));
                System.out.println(
                                "-------------------------------------------------------------------------------------------------------------");
                System.out.printf(
                                "|               |                      |                      |                      |                      |\n");
                System.out.printf("|   %6s      | %12s         |     %12s     |     %12s     |     %12s     |\n",
                                popularCurrencies.get(0),
                                "-",
                                displayText(oldCurrencies, currencies, popularCurrencies.get(0),
                                                popularCurrencies.get(1)),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(0),
                                                popularCurrencies.get(2)),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(0),
                                                popularCurrencies.get(3)));
                System.out.printf(
                                "|               |                      |                      |                      |                      |\n");
                System.out.println(
                                "-------------------------------------------------------------------------------------------------------------");
                System.out.printf(
                                "|               |                      |                      |                      |                      |\n");
                System.out.printf("|   %6s      |     %12s     | %12s         |     %12s     |     %12s     |\n",
                                popularCurrencies.get(1),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(1),
                                                popularCurrencies.get(0)),
                                "-",
                                displayText(oldCurrencies, currencies, popularCurrencies.get(1),
                                                popularCurrencies.get(2)),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(1),
                                                popularCurrencies.get(3)));
                System.out.printf(
                                "|               |                      |                      |                      |                      |\n");
                System.out.println(
                                "-------------------------------------------------------------------------------------------------------------");
                System.out.printf(
                                "|               |                      |                      |                      |                      |\n");
                System.out.printf("|   %6s      |     %12s     |     %12s     | %12s         |     %12s     |\n",
                                popularCurrencies.get(2),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(2),
                                                popularCurrencies.get(0)),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(2),
                                                popularCurrencies.get(1)),
                                "-",
                                displayText(oldCurrencies, currencies, popularCurrencies.get(2),
                                                popularCurrencies.get(3)));
                System.out.printf(
                                "|               |                      |                      |                      |                      |\n");
                System.out.println(
                                "-------------------------------------------------------------------------------------------------------------");
                System.out.printf(
                                "|               |                      |                      |                      |                      |\n");
                System.out.printf("|   %6s      |     %12s     |     %12s     |     %12s     | %12s         |\n",
                                popularCurrencies.get(3),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(3),
                                                popularCurrencies.get(0)),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(3),
                                                popularCurrencies.get(1)),
                                displayText(oldCurrencies, currencies, popularCurrencies.get(3),
                                                popularCurrencies.get(2)),
                                "-");
                System.out.printf(
                                "|               |                      |                      |                      |                      |\n");
                System.out.println(
                                "-------------------------------------------------------------------------------------------------------------");

        }
}