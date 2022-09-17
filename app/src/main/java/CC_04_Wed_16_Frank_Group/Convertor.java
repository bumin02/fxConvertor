package CC_04_Wed_16_Frank_Group;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Convertor {

    private String password = "admin";
    private Boolean isAdmin = false;
    private DataBase db;
    private CurrencyTable ct;

    public Convertor() {

        this.db = new DataBase();
        this.ct = new CurrencyTable(this.db);

    }

    public void runConvertor() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Launching convertor...");

        System.out.print("Are you admin (Y/N)? ");

        String adminInput = sc.next();

        if (!adminInput.toUpperCase().equals("Y") &&
                !adminInput.toUpperCase().equals("N")) {
            System.out.println("\nInvalid input. Please try again.\n");
            runConvertor();
        }

        if (adminInput.toUpperCase().equals("Y")) {

            System.out.print("Enter password: ");

            for (int i = 2; i >= 0; i--) {

                String inputPassword = sc.next();

                if (inputPassword.equals(password)) {
                    isAdmin = true;
                    break;
                } else {
                    System.out.println("  Incorrect password you have " + i + " attempts left.");
                }
            }

            if (isAdmin) {
                System.out.println("------Welcome Admin------");
            } else {
                System.out.println("You are locked out of admin.");
            }

        }

        System.out.println("Entering convertor system...");

        while (sc.hasNextLine()) {

            Boolean toExit = false;

            System.out.println("");
            System.out.println("Choose your option:");
            System.out.println("1. Convert between two currencies");
            System.out.println("2. Print top 4 currency exchange rates");
            System.out.println("3. Summarise conversion rates");
            System.out.println("4. Exit");

            if (isAdmin) {
                System.out.println("5. <ADMIN> Update conversion rates");
                System.out.println("6. <ADMIN> Add new currency");
                System.out.println("7. <ADMIN> Update Popular Currencies");
            }

            int input = sc.nextInt();
            System.out.println("");

            switch (input) {
                case 1:

                    System.out.print("Input convert from currency: ");
                    String cur1 = sc.next().toUpperCase();
                    if (!db.getCurrencyNames().contains(cur1)) {
                        System.out.println(
                                "Invalid currency. We do not store that currency in our system. Please try again.");
                        break;
                    }

                    System.out.print("Input convert to currency: ");
                    String cur2 = sc.next().toUpperCase();
                    if (!db.getCurrencyNames().contains(cur2)) {
                        System.out.println(
                                "Invalid currency. We do not store that currency in our system. Please try again.");
                        break;
                    }

                    System.out.print("Input amount: ");
                    float conversionAmount = sc.nextFloat();

                    System.out.println("Converting $" + conversionAmount + " " + cur1 + " to " + cur2 + ". ");

                    float convertedAmount = db.convertCurrency(cur1, cur2, conversionAmount);

                    System.out.println("Result: $" + convertedAmount + " " + cur2);

                    break;

                case 2:

                    this.ct.display(this.db);
                    // Need to identify whether to display arrows or not

                    break;

                case 3:
                    System.out.print("Enter date 1 (DD/MM/YY): ");
                    String date1 = sc.next();
                    System.out.print("Enter date 2 (DD/MM/YY): ");
                    String date2 = sc.next();
                    System.out.print("Enter currency 1: ");
                    String c1 = sc.next();
                    System.out.print("Enter currency 2: ");
                    String c2 = sc.next();

                    System.out.println("Summary of " + c1 + " and " + c2 + " between " + date1 +
                            " and " + date2 + ": ");

                    // TODO
                    /**
                     * Method to print summary of the conversion rates of 2 currencies
                     * they choose within a specific duration (start and end dates).
                     * This includes all conversion rates, average, median, maximum,
                     * minimum and standard deviation of the conversion rate of the 2
                     * currencies during the specified start and end date.
<<<<<<< HEAD
                     *  e.g. dataBase.summarise(date1, date2, c1, c2);
                    **/
                    System.out.println("Generating summaries:");
                    System.out.println("***************************************************");
                    System.out.println("MINIMUM: From " + c1 +  " to " + c2 + ": " + db.getMin(date1, date2, c1, c2)); 
                    System.out.println("         From " + c2 + " to "  + c1 + ": "  + db.getMin(date1, date2, c2, c1));
                    System.out.println("MAXIMUM: From " + c1 +  " to " + c2 + ": " + db.getMax(date1, date2, c1, c2));
                    System.out.println("         From " + c2 + " to "  + c1 + ": "  +  db.getMax(date1, date2, c2, c1));
                    System.out.println("MEAN:    From " + c1 +  " to " + c2 + ": " + db.getMean(date1, date2, c1, c2));
                    System.out.println("         From " + c2 + " to "  + c1 + ": "  +  db.getMean(date1, date2, c2, c1));
                    System.out.println("MEDIAN:  From " + c1 +  " to " + c2 + ": " + db.getMedian(date1, date2, c1, c2));
                    System.out.println("         From " + c2 + " to "  + c1 + ": "  +  db.getMedian(date1, date2, c2, c1));
                    System.out.println("Standard Deviation:  From " + c1 +  " to " + c2 + ": " + db.getSD(date1, date2, c1, c2));
                    System.out.println("                     From " + c2 + " to "  + c1 + ": "  +  db.getSD(date1, date2, c2, c1));
                    System.out.println("***************************************************");

                    break;
                case 4:
                    System.out.println("Type 1 to exit, anything else to continue");

                    String exNum = sc.next();

                    if (exNum.equals("1")) {
                        toExit = true;
                        db.writeToFile();
                    }
                    System.out.println("-------------------");

                    break;
                case 5:

                    if (isAdmin) {
                        System.out.print("What is the date today (DD/MM/YY): ");
                        String date2Day = sc.next();
                        System.out.print("Which currency do you want to update: ");
                        String currency2Update = sc.next();
                        System.out.print("Enter currency pair to update: ");
                        String secondCurrency2Update = sc.next();
                        System.out.print("Enter new conversion rate: ");
                        double newConversionRate = sc.nextDouble();


                        // TODO
                        /**
                         * Method to
                         * dd new exchange rates daily by entering the date and exchange rate
                         * for that date of all currencies stored in the file. A complete
                         * history of the change exchange rates must be persistent including
                         * the rate and its date of addition. For example, if AUD to SGD was
                         * 0.99 and was added on September 4, 2020 and the admin added another
                         * exchange rate (e.g., 0.97 on September 5, 2020), these rates must
                         * be persisted for the below functionalities.
                         * e.g. database.updateCurrency(date2day, currency2Update);
                         */

                        db.updateCurrency(date2Day, currency2Update, secondCurrency2Update, newConversionRate);
                    }
                    break;

                case 6:
                    if (isAdmin) {
                        System.out.print("What is the date today (DD/MM/YY): ");
                        String date2Day = sc.next();
                        System.out.print("What currency do you want to add: ");
                        String currency2Add = sc.next();

                        HashMap<String, Double> rateForCurr = new HashMap<>();
                        HashMap<String, Double> rateForOtherCurr = new HashMap<>();

                        // TODO
                        /**
                         * Method for the admin to also add new currency types in addition
                         * to the existing currencies and its conversion rates. The most
                         * up-to-date currencies should be used in currency conversion and
                         * in the most popular currencies table.
                         * e.g. database.addCurrency(date2day, currency2Add);
                         */
                        String latest = db.findMostRecentDate();
                        for (Currency c : db.getCurrencies().get(latest)) {
                            System.out.println(String.format("\nWhat is the rate for converting %s to %s\n", currency2Add, c.getName()));
                            rateForCurr.put(c.getName(), Double.parseDouble(sc.next()));
                        }
                        for (Currency c : db.getCurrencies().get(latest)) {
                            System.out.println(String.format("\nWhat is the rate for converting %s to %s\n", c.getName(), currency2Add));
                            rateForOtherCurr.put(c.getName(), Double.parseDouble(sc.next()));
                        }

                        db.addCurrency(date2Day, currency2Add, rateForCurr, rateForOtherCurr);
                    }
                    break;

                case 7:
                    if (isAdmin) {
                        List<String> popularCurrencies = this.db.findPopularCurrencies();
                        System.out.println("Currently, the most popular currencies are: ");

                        int count = 1;
                        for (String currency : popularCurrencies) {
                            System.out.println(count + ". " + currency);
                            count++;
                        }
                        System.out.println("");

                        System.out.println("Choose what current popular currency you would like to remove (1-4):");
                        int removeIndex = sc.nextInt();

                        if (removeIndex > 0 && removeIndex < 5) {
                            popularCurrencies.remove(removeIndex - 1);
                        } else {
                            System.out.println("Invalid input. Please try again.");
                            return;
                        }

                        System.out.println("");
                        System.out.println("You can add the following currencies to the popular currency list: ");

                        List<String> allCurrencies = this.db.getCurrencyNames();
                        allCurrencies.removeAll(popularCurrencies);

                        count = 1;
                        for (String currency : allCurrencies) {
                            System.out.println(count + ". " + currency);
                            count++;
                        }
                        System.out.println("");

                        System.out.println("Choose what currency you would like to add (1-" + count + "):");
                        int addIndex = sc.nextInt();

                        if (addIndex > 0 && addIndex < count) {
                            String addCurrency = allCurrencies.get(addIndex - 1);
                            popularCurrencies.add(addCurrency);
                        } else {
                            System.out.println("Invalid input. Please try again.");
                            return;
                        }

                        System.out.println("");
                        System.out.println("The new popular currencies are: ");
                        count = 1;
                        for (String currency : popularCurrencies) {
                            System.out.println(count + ". " + currency);
                            count++;
                        }

                        this.db.updatePopularCurrencies(popularCurrencies);

                    }
            }

            if (toExit) {
                break;
            }
        }

        System.out.println("Shutting down system...");

        sc.close();

    }

}
