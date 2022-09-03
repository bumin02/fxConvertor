package CC_04_Wed_16_Frank_Group;

import java.util.Scanner;

public class Convertor {

    private String password = "admin";
    private Boolean isAdmin = false;
    private DataBase db;

    public Convertor() {

        this.db = new DataBase();

    }

    public void runConvertor() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Launching convertor...");

        System.out.print("Are you admin (Y/N)? ");

        String adminInput = sc.next();

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
        
        while(sc.hasNextLine()) {

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
            }

            int input = sc.nextInt();
            System.out.println("");
            
            switch (input) {
                case 1:

                    // todo: check for valid currency input
                    System.out.print("Input convert from currency: ");
                    String cur1 = sc.next().toUpperCase();

                    // todo: check for valid currency input
                    System.out.print("Input convert to currency: ");
                    String cur2 = sc.next().toUpperCase();

                    System.out.print("Input amount: ");
                    float conversionAmount = sc.nextFloat();
                    
                    System.out.println("Converting $" + conversionAmount + " " + cur1 + " to " + cur2 + ". ");

                    float convertedAmount = db.convertCurrency(cur1, cur2, conversionAmount);

                    System.out.println("Result: $" + convertedAmount + " " + cur2);

                    break;

                case 2:
                    //TODO
                    // Method here to print table of currencies e.g. currencyTable.displayTable();
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

                    //TODO
                    /**
                     * Method to print summary of the conversion rates of 2 currencies
                     * they choose within a specific duration (start and end dates). 
                     * This includes all conversion rates, average, median, maximum,
                     * minimum and standard deviation of the conversion rate of the 2
                     * currencies during the specified start and end date.
                     *  e.g. dataBase.summarise(date1, date2, c1, c2);
                    **/

                    break;
                case 4:
                    System.out.println("Type 1 to exit, anything else to continue");

                    String exNum = sc.next();

                    if (exNum.equals("1")) {
                        toExit = true;
                    }
                    System.out.println("-------------------");
                    
                    break;
                case 5:
                    if (isAdmin) {
                        System.out.print("What is the date today (DD/MM/YY): ");
                        String date2Day = sc.next();
                        System.out.print("Which currency do you want to update: ");
                        String currency2Update = sc.next();
                        
                        //TODO
                        /**
                         * Method to 
                         * dd new exchange rates daily by entering the date and exchange rate
                         *  for that date of all currencies stored in the file. A complete
                         *  history of the change exchange rates must be persistent including
                         *  the rate and its date of addition. For example, if AUD to SGD was
                         *  0.99 and was added on September 4, 2020 and the admin added another
                         *  exchange rate (e.g., 0.97 on September 5, 2020), these rates must
                         *  be persisted for the below functionalities.
                         *  e.g. database.updateCurrency(date2day, currency2Update);
                         */
                    }
                    break;
                case 6:
                    if (isAdmin) {
                        System.out.print("What is the date today (DD/MM/YY): ");
                        String date2Day = sc.next();
                        System.out.print("What currency do you want to add: ");
                        String currency2Add = sc.next();
                        
                        //TODO
                        /**
                         * Method for the admin to also add new currency types in addition
                         *  to the existing currencies and its conversion rates. The most
                         *  up-to-date currencies should be used in currency conversion and
                         *  in the most popular currencies table.
                         *  e.g. database.addCurrency(date2day, currency2Add);
                         */
                    }
                    break;
            }
            
            if (toExit) {
                break;
            }
        }

        System.out.println("Shutting down system...");

        sc.close();

    }

}
