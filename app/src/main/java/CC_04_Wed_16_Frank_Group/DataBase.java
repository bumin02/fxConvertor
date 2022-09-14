package CC_04_Wed_16_Frank_Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

public class DataBase {

    HashMap<String, List<Currency>> currencies;

    public DataBase() {
        this.currencies = new HashMap<>();
        initialiseData();
    }

    public void initialiseData() {
        try {
            File file = new File("src/main/java/CC_04_Wed_16_Frank_Group/initialData.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String date = "";
            ArrayList<Currency> curr = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                if (line.contains("date")) {

                    if (curr.size() > 0 && !date.equals("")) {
                        this.currencies.put(date, curr);
                        date = line.substring(5);
                        curr = new ArrayList<>();
                    } else {
                        date = line.substring(5);
                        curr = new ArrayList<>();
                    }

                    continue;

                }

                String[] arrOfStr = line.split(" ");
                int i = 0;
                Currency currency = null;

                for (String a : arrOfStr) {
                    if (i == 0) {
                        currency = new Currency(a);
                        curr.add(currency);
                    } else {
                        String[] parser = a.split(":");
                        currency.addConversionRate(parser[0], Double.parseDouble(parser[1]));
                    }

                    i += 1;
                }
            }

            this.currencies.put(date, curr);

            // close file
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, List<Currency>> getCurrencies() {
        return this.currencies;
    }

    public List<String> getCurrencyNames() {

        List<String> currencyNames = new ArrayList<>();

        for (String key : this.currencies.keySet()) {

            for (Currency currency : this.currencies.get(key)) {

                if (!currencyNames.contains(currency.getName())) {
                    currencyNames.add(currency.getName());
                }
            }

        }

        return currencyNames;

    }

    public float convertCurrency(String inputCurrency, String outputCurrency, float amount) {

        // find string date that is most recent
        String mostRecentDateString = findMostRecentDate();

        // find input currency on date
        Currency inputCurr = null;
        for (Currency c : this.currencies.get(mostRecentDateString)) {
            if (c.getName().equals(inputCurrency)) {
                inputCurr = c;
            }
        }

        // find conversion rate to output currency
        if (inputCurr == null) {
            System.out.println("Cannot find currency\n");
            return -1;
        }

        float conversionRate = 0;
        for (String key : inputCurr.getConversionRates().keySet()) {
            if (key.equals(outputCurrency)) {
                conversionRate = inputCurr.getConversionRates().get(key).floatValue();
            }
        }

        // return converted amount
        return amount * conversionRate;

    }

    public String findMostRecentDate() {

        Date mostRecentDate = new Date(0);
        String mostRecentDateString = "";
        for (String key : this.currencies.keySet()) {
            Date date = new Date(key);
            if (date.after(mostRecentDate)) {
                mostRecentDate = date;
                mostRecentDateString = key;
            }
        }

        return mostRecentDateString;

    }

    // needed for comparisons in table
    public String findSecondMostRecentDate() {

        Date mostRecentDate = new Date(0);
        String mostRecentDateString = "";
        for (String key : this.currencies.keySet()) {
            Date date = new Date(key);
            if (date.after(mostRecentDate)) {
                mostRecentDate = date;
                mostRecentDateString = key;
            }
        }

        Date secondMostRecentDate = new Date(0);
        String secondMostRecentDateString = "";
        for (String key : this.currencies.keySet()) {
            Date date = new Date(key);
            if (date.after(secondMostRecentDate) && !date.equals(mostRecentDate)) {
                secondMostRecentDate = date;
                secondMostRecentDateString = key;
            }
        }

        return secondMostRecentDateString;
    }

    public Currency findCurrency(String name, List<Currency> ls) {
        for (Currency c : ls) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    // update the rate of currency1 in currency2's hashmap
    // please check the format of date prior to this method
    // return 1 if successful
    // 0 if input error, particularly null data
    // 2 if no currency is found
    // 3 if currency1 not found
    public int updateCurrency(String date, String currency1, String currency2, Double newRate1) {
        // check all input not null
        if (date == null || currency2 == null || currency1 == null || newRate1 == null) {
            return 0;
        }

        String latest = findMostRecentDate();
        List<Currency> cloned = new ArrayList<>();

        // change the rate
        Currency curr2 = findCurrency(currency2, this.currencies.get(latest));
        if (curr2 == null) {
            return 2;
        }

        // update data
        if (!curr2.getConversionRates().containsKey(currency1)) {
            return 3;
        }

        // create new currency and add to list
        Currency c = new Currency(currency2);
        cloned.add(c);

        for (Currency i: this.currencies.get(latest)) {
            if (i != curr2) {
                cloned.add(i);
            }
        }

        // update value in the new currency object
        for (String i: curr2.getConversionRates().keySet()) {
            if (!i.equals(currency1)) {
                c.getConversionRates().put(i, curr2.getConversionRates().get(i));
            }
        }
        c.getConversionRates().put(currency1, newRate1);
        this.currencies.put(date, cloned);
        return 1;
    }

}